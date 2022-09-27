package com.muhammadtalhaminhas.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private RecyclerView skillist;
    private ArrayList<String> skills;
    private  SkillsAdapter skillsadapter;
    private Button up;
    private Spinner skill;
    private int selected_course_index;
    private ArrayAdapter aa;
    List<String> temp = Arrays.asList(
            "General Science",
            "Mathematics","Pakistan Studies","Islamiyat / Ethics","English","Geography of Pakistan",
            "History of Pakistan","Education","Food & Nutrition","Child Development & Family Living",
            "Art & Model Drawing","Islamic History","Civics","Arabic","Urdu","Elements of Home Economics",
            "Essay","General Knowledge ","Everyday Science ","Current Affairs Pakistan","Affairs Islamiat",
            "Accountancy and Auditing","Economics","Business Administration","Public Administration","Political Science",
            "Agriculture","Forestry","Sociology","Journalism","Pure Mathematics","Applied Mathematics","Computer Science",
            "Statistics","Geology","Geography","Chemistry","Botany","Zoology","Islamic History and Culture","Indo Pak History",
            "British History","European History","American History","Law","Constitutional Law","Mercantile Law","Muslim Law and Jurisprudence",
            "International Law","International Relations","Philosophy","Psychology","Sindhi","Pushto","Punjabi","Balochi",
            "Operating System Case Study","Natural Language Processing","Distributed Database Systems","Design and Analysis of Algorithms",
            "Financial Management","Multimedia Systems","Computational Linear Algebra (Optional)",
            "Theory of Operating Systems","Compiler Construction - II","Advanced Computer Graphics (Optional)","Financial Accounting",
            "Microcomputer Design & Interfacing - II","BSCS-611","Parallel Computing","Management Information System");



    ArrayList<String> subjects = new ArrayList<String >();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makegig);


        subjects.addAll(temp);
        Collections.sort(subjects);
        up=findViewById(R.id.up);
        skill=findViewById(R.id.skill);

        skill.setOnItemSelectedListener(this);
        //Creating the ArrayAdapter instance having the country list
        aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,subjects);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        skill.setAdapter(aa);

        skills=new ArrayList<String>();
        skillist = (RecyclerView) findViewById(R.id.skill_list);
        skillist.setHasFixedSize(true);
        skillist.setLayoutManager(new LinearLayoutManager(this));
        skillsadapter=new SkillsAdapter(skills);
        skillist.setAdapter(skillsadapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                removeItem(viewHolder.getAdapterPosition());

            }
        }).attachToRecyclerView(skillist);

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    skills.add(subjects.get(selected_course_index));
                    skillsadapter.notifyItemInserted(skills.size()-1);
                    skillist.scrollToPosition(skills.size()-1);
                subjects.remove(selected_course_index);
                Collections.sort(subjects);
                aa.notifyDataSetChanged();

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        // TODO Auto-generated method stub
        selected_course_index=position;
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    private void removeItem(int index) {
        subjects.add(selected_course_index,skills.get(index));
        skills.remove(index);
        skillsadapter.notifyItemRemoved(index);
        Collections.sort(subjects);
        aa.notifyDataSetChanged();
    }
}
