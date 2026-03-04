/*     */ package org.zendev.SupperSeason.Utils.Updater;
/*     */ 
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ 
/*     */ public class KeyBuilder
/*     */ {
/*     */   private final FileConfiguration config;
/*     */   private final char separator;
/*     */   private final StringBuilder builder;
/*     */   
/*     */   public KeyBuilder(FileConfiguration config, char separator) {
/*  12 */     this.config = config;
/*  13 */     this.separator = separator;
/*  14 */     this.builder = new StringBuilder();
/*     */   }
/*     */   
/*     */   private KeyBuilder(KeyBuilder keyBuilder) {
/*  18 */     this.config = keyBuilder.config;
/*  19 */     this.separator = keyBuilder.separator;
/*  20 */     this.builder = new StringBuilder(keyBuilder.toString());
/*     */   }
/*     */   
/*     */   public void parseLine(String line) {
/*  24 */     line = line.trim();
/*  25 */     String[] currentSplitLine = line.split(":");
/*  26 */     String key = currentSplitLine[0].replace("'", "").replace("\"", "");
/*     */ 
/*     */ 
/*     */     
/*  30 */     while (this.builder.length() > 0 && !this.config.contains(String.valueOf(this.builder.toString()) + this.separator + key)) {
/*  31 */       removeLastKey();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  36 */     if (this.builder.length() > 0) {
/*  37 */       this.builder.append(this.separator);
/*     */     }
/*     */ 
/*     */     
/*  41 */     this.builder.append(key);
/*     */   }
/*     */   
/*     */   public String getLastKey() {
/*  45 */     if (this.builder.length() == 0) {
/*  46 */       return "";
/*     */     }
/*  48 */     return this.builder.toString().split("[" + this.separator + "]")[0];
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  52 */     return (this.builder.length() == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSubKeyOf(String parentKey) {
/*  57 */     return isSubKeyOf(parentKey, this.builder.toString(), this.separator);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSubKey(String subKey) {
/*  62 */     return isSubKeyOf(this.builder.toString(), subKey, this.separator);
/*     */   }
/*     */   
/*     */   public static boolean isSubKeyOf(String parentKey, String subKey, char separator) {
/*  66 */     if (parentKey.isEmpty()) {
/*  67 */       return false;
/*     */     }
/*  69 */     return (subKey.startsWith(parentKey) && 
/*  70 */       subKey.substring(parentKey.length()).startsWith(String.valueOf(separator)));
/*     */   }
/*     */   
/*     */   public static String getIndents(String key, char separator) {
/*  74 */     String[] splitKey = key.split("[" + separator + "]");
/*  75 */     StringBuilder builder = new StringBuilder();
/*     */     
/*  77 */     for (int i = 1; i < splitKey.length; i++) {
/*  78 */       builder.append("  ");
/*     */     }
/*     */     
/*  81 */     return builder.toString();
/*     */   }
/*     */   
/*     */   public boolean isConfigSection() {
/*  85 */     String key = this.builder.toString();
/*  86 */     return this.config.isConfigurationSection(key);
/*     */   }
/*     */   
/*     */   public boolean isConfigSectionWithKeys() {
/*  90 */     String key = this.builder.toString();
/*  91 */     return (this.config.isConfigurationSection(key) && !this.config.getConfigurationSection(key).getKeys(false).isEmpty());
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeLastKey() {
/*  96 */     if (this.builder.length() == 0) {
/*     */       return;
/*     */     }
/*  99 */     String keyString = this.builder.toString();
/*     */     
/* 101 */     String[] split = keyString.split("[" + this.separator + "]");
/*     */     
/* 103 */     int minIndex = Math.max(0, this.builder.length() - split[split.length - 1].length() - 1);
/* 104 */     this.builder.replace(minIndex, this.builder.length(), "");
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 109 */     return this.builder.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   protected KeyBuilder clone() {
/* 114 */     return new KeyBuilder(this);
/*     */   }
/*     */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/Utils/Updater/KeyBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */