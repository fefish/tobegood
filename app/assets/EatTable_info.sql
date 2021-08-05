BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "EatTable_info" (
	"RecipeId"	INTEGER NOT NULL,
	"RecipeOneName"	TEXT,
	"RecipeOnePic"	TEXT,
	"RecipeOneContent"	TEXT,
	"RecipeTwoName"	TEXT,
	"RecipeTwoPic"	TEXT,
	"RecipeTwoContent"	TEXT,
	"RecipeThreeName"	TEXT,
	"RecipeThreePic"	TEXT,
	"RecipeThreeContent"	TEXT,
	PRIMARY KEY("RecipeId")
);
INSERT INTO "EatTable_info" VALUES (1,'Recipe1Name1','Recipe001','Recipe1Content1','Recipe1Name2','Recipe001','Recipe1Content2','Recipe1Name3','Recipe001','Recipe1Content3');
INSERT INTO "EatTable_info" VALUES (2,'Recipe2Name1','Recipe002','Recipe2Content1','Recipe2Name2','Recipe002','Recipe2Content2','Recipe2Name3','Recipe002','Recipe2Content3');
INSERT INTO "EatTable_info" VALUES (3,'Recipe3Name1','Recipe003','Recipe3Content1','Recipe3Name2','Recipe003','Recipe3Content2','Recipe3Name3','Recipe003','Recipe3Content3');
INSERT INTO "EatTable_info" VALUES (4,'Recipe4Name1','Recipe004','Recipe4Content1','Recipe4Name2','Recipe004','Recipe4Content2','Recipe4Name3','Recipe004','Recipe4Content3');
INSERT INTO "EatTable_info" VALUES (5,'Recipe5Name1','Recipe005','Recipe5Content1','Recipe5Name2','Recipe005','Recipe5Content2','Recipe5Name3','Recipe005','Recipe5Content3');
COMMIT;
