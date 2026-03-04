/*     */ package org.zendev.SupperSeason.Files;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.zendev.SupperSeason.Utils.Season;
/*     */ import org.zendev.SupperSeason.Utils.Utils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class File_time
/*     */ {
/*     */   public static int Day;
/*     */   public static int Month;
/*     */   public static int Year;
/*     */   public static String Date;
/*     */   public static Season ss;
/*     */   public static File timFile;
/*     */   public static FileConfiguration timConfig;
/*     */   
/*     */   public static void save() {
/*     */     try {
/*  25 */       timConfig.save(timFile);
/*     */     }
/*  27 */     catch (IOException e) {
/*     */       
/*  29 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void loadDMY() {
/*  35 */     Day = timConfig.getInt("Date.day");
/*  36 */     Month = timConfig.getInt("Date.month");
/*  37 */     Year = timConfig.getInt("Date.year");
/*     */   }
/*     */ 
/*     */   
/*     */   public static void addDay(int num) {
/*  42 */     Day += num;
/*  43 */     timConfig.set("Date.day", Day);
/*  44 */     save();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void loadDate() {
/*  49 */     Date = timConfig.getString("Date.format").replace("<dd>", String.valueOf(Day)).replace("<mm>", String.valueOf(Month)).replace("<yy>", String.valueOf(Year));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void calculate() {
/*  54 */     while (Day > 30) {
/*     */       
/*  56 */       Month++;
/*  57 */       Day -= 30;
/*     */     } 
/*  59 */     while (Month > 12) {
/*     */       
/*  61 */       Year++;
/*  62 */       Month -= 12;
/*     */     } 
/*  64 */     timConfig.set("Date.day", Day);
/*  65 */     timConfig.set("Date.month", Month);
/*  66 */     timConfig.set("Date.year", Year);
/*  67 */     save();
/*     */   }
/*     */ 
/*     */   
/*     */   public static String barName() {
/*  72 */     return Utils.chat(String.valueOf(loadDisplay(ss)) + " &a- " + Date);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void loadSeason() {
/*  77 */     if (Month >= 1 && Month <= 3) {
/*  78 */       ss = Season.SPRING;
/*  79 */     } else if (Month >= 4 && Month <= 6) {
/*  80 */       ss = Season.SUMMER;
/*  81 */     } else if (Month >= 7 && Month <= 9) {
/*  82 */       ss = Season.AUTUMN;
/*  83 */     } else if (Month >= 10 && Month <= 12) {
/*  84 */       ss = Season.WINTER;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String loadDisplay(Season ss) {
/*  89 */     return File_config.getSeasonName(ss);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String loadId(Season ss) {
/*  94 */     switch (ss) {
/*     */       case SPRING:
/*  96 */         return "spring";
/*  97 */       case SUMMER: return "summer";
/*  98 */       case AUTUMN: return "autumn";
/*  99 */       case WINTER: return "winter";
/* 100 */     }  return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void loadTime() {
/* 106 */     loadDMY();
/* 107 */     calculate();
/* 108 */     loadSeason();
/* 109 */     loadDate();
/*     */   }
/*     */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/Files/File_time.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */