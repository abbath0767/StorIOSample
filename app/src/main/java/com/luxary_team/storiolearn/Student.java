package com.luxary_team.storiolearn;


import android.support.annotation.Nullable;

import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

import org.jetbrains.annotations.NotNull;

@StorIOSQLiteType(table = StudentTable.TABLE)
public class Student {

    @Nullable
    @StorIOSQLiteColumn(name = StudentTable.COLUMN_ID, key = true)
    Long id;

    @NotNull
    @StorIOSQLiteColumn(name = StudentTable.COLUMN_NAME)
    String name;

    @NotNull
    @StorIOSQLiteColumn(name = StudentTable.COLUMN_AVERAGE)
    Integer average;

    Student() {}

    private Student(@Nullable Long id, @NotNull String name, @NotNull Integer average) {
        this.id = id;
        this.name = name;
        this.average = average;
    }

    public static Student newStudent(@Nullable Long id, @NotNull String name, @NotNull Integer average) {
        return new Student(id, name, average);
    }

    public static Student newStudent(@NotNull String name, @NotNull Integer average) {
        return new Student(null, name, average);
    }

    @Nullable Long id() {
        return id;
    }

    @Nullable String name() {
        return name;
    }

    @Nullable Integer average() {
        return average;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Student student = (Student) obj;

        if (id != null ? !id.equals(student.id) : student.id != null) return false;
        if (!name.equals(student.name)) return false;

        return average.equals(student.average);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + name.hashCode();
        result = 31 * result + average.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", average=" + average +
                '}';
    }
}

