/*     */ package org.zendev.SupperSeason.Utils.Updater;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.StringWriter;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Path;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UpdateConfig
/*     */ {
/*     */   private static final char SEPARATOR = '.';
/*     */   
/*     */   public static void update(Plugin plugin, String resourceName, File toUpdate, String... ignoredSections) throws IOException {
/*  32 */     update(plugin, resourceName, toUpdate, Arrays.asList(ignoredSections));
/*     */   }
/*     */   
/*     */   public static void update(Plugin plugin, String resourceName, File toUpdate, List<String> ignoredSections) throws IOException {
/*  36 */     Preconditions.checkArgument(toUpdate.exists(), "The toUpdate file doesn't exist!");
/*     */     
/*  38 */     YamlConfiguration yamlConfiguration1 = YamlConfiguration.loadConfiguration(new InputStreamReader(plugin.getResource(resourceName), StandardCharsets.UTF_8));
/*  39 */     YamlConfiguration yamlConfiguration2 = YamlConfiguration.loadConfiguration(toUpdate);
/*  40 */     Map<String, String> comments = parseComments(plugin, resourceName, (FileConfiguration)yamlConfiguration1);
/*  41 */     Map<String, String> ignoredSectionsValues = parseIgnoredSections(toUpdate, (FileConfiguration)yamlConfiguration2, comments, (ignoredSections == null) ? Collections.<String>emptyList() : ignoredSections);
/*     */ 
/*     */     
/*  44 */     StringWriter writer = new StringWriter();
/*  45 */     write((FileConfiguration)yamlConfiguration1, (FileConfiguration)yamlConfiguration2, new BufferedWriter(writer), comments, ignoredSectionsValues);
/*  46 */     String value = writer.toString();
/*     */     
/*  48 */     Path toUpdatePath = toUpdate.toPath();
/*  49 */     if (!value.equals(new String(Files.readAllBytes(toUpdatePath), StandardCharsets.UTF_8))) {
/*  50 */       Files.write(toUpdatePath, value.getBytes(StandardCharsets.UTF_8), new java.nio.file.OpenOption[0]);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static void write(FileConfiguration defaultConfig, FileConfiguration currentConfig, BufferedWriter writer, Map<String, String> comments, Map<String, String> ignoredSectionsValues) throws IOException {
/*  56 */     YamlConfiguration yamlConfiguration = new YamlConfiguration();
/*     */     
/*  58 */     label38: for (String fullKey : (Iterable<String>)defaultConfig.getKeys(true)) {
/*  59 */       String indents = KeyBuilder.getIndents(fullKey, '.');
/*     */       
/*  61 */       if (ignoredSectionsValues.isEmpty()) {
/*  62 */         writeCommentIfExists(comments, writer, fullKey, indents);
/*     */       } else {
/*  64 */         for (Map.Entry<String, String> entry : ignoredSectionsValues.entrySet()) {
/*  65 */           if (((String)entry.getKey()).equals(fullKey)) {
/*  66 */             writer.write(String.valueOf((String)ignoredSectionsValues.get(fullKey)) + "\n"); continue label38;
/*     */           } 
/*  68 */           if (KeyBuilder.isSubKeyOf(entry.getKey(), fullKey, '.')) {
/*     */             continue label38;
/*     */           }
/*     */         } 
/*     */         
/*  73 */         writeCommentIfExists(comments, writer, fullKey, indents);
/*     */       } 
/*     */       
/*  76 */       Object currentValue = currentConfig.get(fullKey);
/*     */       
/*  78 */       if (currentValue == null) {
/*  79 */         currentValue = defaultConfig.get(fullKey);
/*     */       }
/*  81 */       String[] splitFullKey = fullKey.split("[.]");
/*  82 */       String trailingKey = splitFullKey[splitFullKey.length - 1];
/*     */       
/*  84 */       if (currentValue instanceof ConfigurationSection) {
/*  85 */         writer.write(String.valueOf(indents) + trailingKey + ":");
/*     */         
/*  87 */         if (!((ConfigurationSection)currentValue).getKeys(false).isEmpty()) {
/*  88 */           writer.write("\n"); continue;
/*     */         } 
/*  90 */         writer.write(" {}\n");
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/*  95 */       yamlConfiguration.set(trailingKey, currentValue);
/*  96 */       String yaml = yamlConfiguration.saveToString();
/*  97 */       yaml = yaml.substring(0, yaml.length() - 1).replace("\n", "\n" + indents);
/*  98 */       String toWrite = String.valueOf(indents) + yaml + "\n";
/*  99 */       yamlConfiguration.set(trailingKey, null);
/* 100 */       writer.write(toWrite);
/*     */     } 
/*     */     
/* 103 */     String danglingComments = comments.get(null);
/*     */     
/* 105 */     if (danglingComments != null) {
/* 106 */       writer.write(danglingComments);
/*     */     }
/* 108 */     writer.close();
/*     */   }
/*     */ 
/*     */   
/*     */   private static Map<String, String> parseComments(Plugin plugin, String resourceName, FileConfiguration defaultConfig) throws IOException {
/* 113 */     BufferedReader reader = new BufferedReader(new InputStreamReader(plugin.getResource(resourceName)));
/* 114 */     Map<String, String> comments = new LinkedHashMap<>();
/* 115 */     StringBuilder commentBuilder = new StringBuilder();
/* 116 */     KeyBuilder keyBuilder = new KeyBuilder(defaultConfig, '.');
/*     */     
/*     */     String line;
/* 119 */     while ((line = reader.readLine()) != null) {
/* 120 */       String trimmedLine = line.trim();
/*     */ 
/*     */       
/* 123 */       if (trimmedLine.startsWith("-")) {
/*     */         continue;
/*     */       }
/*     */       
/* 127 */       if (trimmedLine.isEmpty() || trimmedLine.startsWith("#")) {
/* 128 */         commentBuilder.append(trimmedLine).append("\n"); continue;
/*     */       } 
/* 130 */       keyBuilder.parseLine(trimmedLine);
/* 131 */       String key = keyBuilder.toString();
/*     */ 
/*     */       
/* 134 */       if (commentBuilder.length() > 0) {
/* 135 */         comments.put(key, commentBuilder.toString());
/* 136 */         commentBuilder.setLength(0);
/*     */       } 
/*     */ 
/*     */       
/* 140 */       if (!keyBuilder.isConfigSectionWithKeys()) {
/* 141 */         keyBuilder.removeLastKey();
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 146 */     reader.close();
/*     */     
/* 148 */     if (commentBuilder.length() > 0) {
/* 149 */       comments.put(null, commentBuilder.toString());
/*     */     }
/* 151 */     return comments;
/*     */   }
/*     */   
/*     */   private static Map<String, String> parseIgnoredSections(File toUpdate, FileConfiguration currentConfig, Map<String, String> comments, List<String> ignoredSections) throws IOException {
/* 155 */     BufferedReader reader = new BufferedReader(new FileReader(toUpdate));
/* 156 */     Map<String, String> ignoredSectionsValues = new LinkedHashMap<>(ignoredSections.size());
/* 157 */     KeyBuilder keyBuilder = new KeyBuilder(currentConfig, '.');
/* 158 */     StringBuilder valueBuilder = new StringBuilder();
/*     */     
/* 160 */     String currentIgnoredSection = null;
/*     */     String line;
/* 162 */     while ((line = reader.readLine()) != null) {
/* 163 */       String trimmedLine = line.trim();
/*     */       
/* 165 */       if (trimmedLine.isEmpty() || trimmedLine.startsWith("#")) {
/*     */         continue;
/*     */       }
/* 168 */       if (trimmedLine.startsWith("-")) {
/* 169 */         for (String ignoredSection : ignoredSections) {
/* 170 */           boolean isIgnoredParent = ignoredSection.equals(keyBuilder.toString());
/*     */           
/* 172 */           if (isIgnoredParent || keyBuilder.isSubKeyOf(ignoredSection)) {
/* 173 */             valueBuilder.append("\n").append(line);
/*     */             
/*     */             break label43;
/*     */           } 
/*     */         } 
/*     */       }
/* 179 */       keyBuilder.parseLine(trimmedLine);
/* 180 */       String fullKey = keyBuilder.toString();
/*     */ 
/*     */ 
/*     */       
/* 184 */       if (currentIgnoredSection != null && !KeyBuilder.isSubKeyOf(currentIgnoredSection, fullKey, '.')) {
/* 185 */         ignoredSectionsValues.put(currentIgnoredSection, valueBuilder.toString());
/* 186 */         valueBuilder.setLength(0);
/* 187 */         currentIgnoredSection = null;
/*     */       } 
/*     */       
/* 190 */       label43: for (String ignoredSection : ignoredSections) {
/* 191 */         boolean isIgnoredParent = ignoredSection.equals(fullKey);
/*     */         
/* 193 */         if (isIgnoredParent || keyBuilder.isSubKeyOf(ignoredSection)) {
/* 194 */           if (valueBuilder.length() > 0) {
/* 195 */             valueBuilder.append("\n");
/*     */           }
/* 197 */           String comment = comments.get(fullKey);
/*     */           
/* 199 */           if (comment != null) {
/* 200 */             String indents = KeyBuilder.getIndents(fullKey, '.');
/* 201 */             valueBuilder.append(indents).append(comment.replace("\n", "\n" + indents));
/* 202 */             valueBuilder.setLength(valueBuilder.length() - indents.length());
/*     */           } 
/*     */           
/* 205 */           valueBuilder.append(line);
/*     */ 
/*     */ 
/*     */           
/* 209 */           if (isIgnoredParent) {
/* 210 */             currentIgnoredSection = fullKey;
/*     */           }
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 217 */     reader.close();
/*     */     
/* 219 */     if (valueBuilder.length() > 0) {
/* 220 */       ignoredSectionsValues.put(currentIgnoredSection, valueBuilder.toString());
/*     */     }
/* 222 */     return ignoredSectionsValues;
/*     */   }
/*     */   
/*     */   private static void writeCommentIfExists(Map<String, String> comments, BufferedWriter writer, String fullKey, String indents) throws IOException {
/* 226 */     String comment = comments.get(fullKey);
/*     */ 
/*     */     
/* 229 */     if (comment != null)
/*     */     {
/* 231 */       writer.write(String.valueOf(indents) + comment.substring(0, comment.length() - 1).replace("\n", "\n" + indents) + "\n");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static void removeLastKey(StringBuilder keyBuilder) {
/* 237 */     if (keyBuilder.length() == 0) {
/*     */       return;
/*     */     }
/* 240 */     String keyString = keyBuilder.toString();
/*     */     
/* 242 */     String[] split = keyString.split("[.]");
/*     */     
/* 244 */     int minIndex = Math.max(0, keyBuilder.length() - split[split.length - 1].length() - 1);
/* 245 */     keyBuilder.replace(minIndex, keyBuilder.length(), "");
/*     */   }
/*     */ 
/*     */   
/*     */   private static void appendNewLine(StringBuilder builder) {
/* 250 */     if (builder.length() > 0)
/* 251 */       builder.append("\n"); 
/*     */   }
/*     */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/Utils/Updater/UpdateConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */