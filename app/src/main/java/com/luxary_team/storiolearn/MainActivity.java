package com.luxary_team.storiolearn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.impl.DefaultStorIOSQLite;
import com.pushtorefresh.storio.sqlite.operations.put.PutResult;
import com.pushtorefresh.storio.sqlite.operations.put.PutResults;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.student_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.add_student)
    Button mAddButton;
    @BindView(R.id.edit_student_name)
    EditText mNameEditText;
    @BindView(R.id.edit_student_average)
    EditText mAverageEditText;

    private StorIOSQLite mStorIOSQLite;

    private StudentAdapter mStudentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        init();
    }

    private void init() {
        initSQL();
        initRecycler();
    }

    private void initSQL() {
        mStorIOSQLite = DefaultStorIOSQLite.builder()
                .sqliteOpenHelper(new DBOpenHelper(this))
                .addTypeMapping(Student.class, new StudentSQLiteTypeMapping())
                .build();
    }

    private void initRecycler() {
        mStudentAdapter = new StudentAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mStudentAdapter);
        updateList();
    }

    @OnClick(R.id.add_mock)
    public void addStudents() {
        final List<Student> students = new ArrayList<Student>();

        for (int i = 0; i < 10; i++) {
            students.add(Student.newStudent("name is " + i, i));
        }

        PutResults<Student> results = mStorIOSQLite
                .put()
                .objects(students)
                .prepare()
                .executeAsBlocking();


        Log.d("TAG", "size: " + results.results().size());

        updateList();
    }

    @OnClick(R.id.add_student)
    public void addStudent() {
        String value = mNameEditText.getText().toString().trim();
        value = (value == null || value.equals("")) ?
                "default value" : value;

        String averageStr = mAverageEditText.getText().toString().trim();
        Integer averageInt = averageStr.equals("") ? 0 : Integer.valueOf(averageStr);

        final Student student = Student.newStudent(value,
                averageInt);

        Log.d("TAG", "prepear student with name " + student.name() + " and average: " + student.average());

        PutResult result = mStorIOSQLite
                .put()
                .object(student)
                .prepare()
                .executeAsBlocking();

        Log.d("TAG", "Student with name " + student.name() + " added");

        updateList();
        mStudentAdapter.notifyDataSetChanged();
    }

    private void updateList() {
        List<Student> list = mStorIOSQLite
                .get()
                .listOfObjects(Student.class)
                .withQuery(StudentTable.QUERY_ALL)
                .prepare()
                .executeAsBlocking();

        mStudentAdapter.setStudents(list);
    }
}
