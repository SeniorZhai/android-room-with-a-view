package com.example.android.roomwordssample;

/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * A basic class representing an entity that is a row in a one-column database table.
 *
 * @ Entity - You must annotate the class as an entity and supply a table name if not class name.
 * @ PrimaryKey - You must identify the primary key.
 * @ ColumnInfo - You must supply the column name if it is different from the variable name.
 *
 * See the documentation for the full rich set of annotations.
 * https://developer.android.com/topic/libraries/architecture/room.html
 */

@Entity(tableName = "word_table") public class Word {

  @PrimaryKey @NonNull @ColumnInfo(name = "word") public String mWord;
  @ColumnInfo(name = "column1") public String column1 = "column1";
  @ColumnInfo(name = "column2") public String column2 = "column2";
  @ColumnInfo(name = "column3") public String column3 = "column3";
  @ColumnInfo(name = "column4") public String column4 = "column4";
  @ColumnInfo(name = "column5") public String column5 = "column5";
  @ColumnInfo(name = "column6") public String column6 = "column6";
  @ColumnInfo(name = "column7") public String column7 = "column7";
  @ColumnInfo(name = "column8") public String column8 = "column8";
  @ColumnInfo(name = "column9") public String column9 = "column9";
  @ColumnInfo(name = "column10") public String column10 = "column10";
  @ColumnInfo(name = "column11") public String column11 = "column11";
  @ColumnInfo(name = "column12") public String column12 = "column12";
  @ColumnInfo(name = "column13") public String column13 = "column13";
  @ColumnInfo(name = "column14") public String column14 = "column14";
  @ColumnInfo(name = "column15") public String column15 = "column15";
  @ColumnInfo(name = "column16") public String column16 = "column16";
  @ColumnInfo(name = "column17") public String column17 = "column17";
  @ColumnInfo(name = "column18") public String column18 = "column18";
  @ColumnInfo(name = "column19") public String column19 = "column19";
  @ColumnInfo(name = "column20") public String column20 = "column20";

  public Word(@NonNull String word) {
    this.mWord = word;
  }

  public String getWord() {
    return this.mWord;
  }
}