package kr.ac.cnu.cse.termproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private AnimatedExpandableListView mainExpandableListView;
    private ExampleAdapter adapter;
    Button generateButton;
    public static final int REQUEST_CODE_ADD_PROJECT = 100;
    public static final int REQUEST_CODE_MANAGE_PROJECT = 99;
    public static final int REQUEST_CODE_GENERATE = 98;
    public static final int REQUEST_CODE_USERINFO = 97;
    ActionBar abar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<GroupItem> items = new ArrayList<GroupItem>();

        abar = this.getSupportActionBar();
        abar.show();
        abar.setTitle("포 달");

        // Populate our list with groups and it's children
        for(int i = 1; i < 3; i++) {
            GroupItem item = new GroupItem();

            item.title = "Group " + i;

            for(int j = 0; j < i; j++) {
                ChildItem child = new ChildItem();
                child.title = "Awesome item " + j;

                item.items.add(child);
            }
            ChildItem addButton = new ChildItem();
            addButton.title = "+";
            item.items.add(addButton);

            items.add(item);
        }

        adapter = new ExampleAdapter(this);
        adapter.setData(items);

        mainExpandableListView = (AnimatedExpandableListView) findViewById(R.id.mainExpandableListView);
        mainExpandableListView.setAdapter(adapter);

        // In order to show animations, we need to use a custom click handler
        // for our ExpandableListView.
        mainExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                // We call collapseGroupWithAnimation(int) and
                // expandGroupWithAnimation(int) to animate group
                // expansion/collapse.

                if (mainExpandableListView.isGroupExpanded(groupPosition)) {
                    mainExpandableListView.collapseGroupWithAnimation(groupPosition);
                } else {
                    if (groupPosition == 1){
                        if (mainExpandableListView.isGroupExpanded(0)){
                            mainExpandableListView.collapseGroupWithAnimation(0);
                        }
                    }else{
                        if (mainExpandableListView.isGroupExpanded(1)){
                            mainExpandableListView.collapseGroupWithAnimation(1);
                        }
                    }
                    mainExpandableListView.expandGroupWithAnimation(groupPosition);
                }
                return true;
            }
        });

        mainExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                if (i==0 && i1 ==1){
                    Intent intent = new Intent(getApplicationContext(), AddProjectActivity.class);
                    startActivityForResult(intent,REQUEST_CODE_ADD_PROJECT);
                }else{
                    Intent intent = new Intent(getApplicationContext(), ManageProjectActivity.class);
                    startActivityForResult(intent,REQUEST_CODE_MANAGE_PROJECT);
                }
                return true;
            }
        });

        mainExpandableListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),"long click",Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        generateButton = (Button)findViewById(R.id.generateButton);
        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PortfolioGenerateActivity.class);
                startActivityForResult(intent,REQUEST_CODE_GENERATE);
            }
        });
    }

    private static class GroupItem {
        String title;
        List<ChildItem> items = new ArrayList<ChildItem>();
    }

    private static class ChildItem {
        String title;
    }

    private static class ChildHolder {
        TextView title;
    }

    private static class GroupHolder {
        TextView title;
    }

    /**
     * Adapter for our list of {@link GroupItem}s.
     */
    private class ExampleAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter {
        private LayoutInflater inflater;

        private List<GroupItem> items;

        public ExampleAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        public void setData(List<GroupItem> items) {
            this.items = items;
        }

        @Override
        public ChildItem getChild(int groupPosition, int childPosition) {
            return items.get(groupPosition).items.get(childPosition);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ChildHolder holder;
            ChildItem item = getChild(groupPosition, childPosition);
            if (convertView == null) {
                holder = new ChildHolder();
                convertView = inflater.inflate(R.layout.main_project, parent, false);
                holder.title = (TextView) convertView.findViewById(R.id.btnProject_text);
                convertView.setTag(holder);
            } else {
                holder = (ChildHolder) convertView.getTag();
            }

            holder.title.setText(item.title);

            return convertView;
        }

        @Override
        public int getRealChildrenCount(int groupPosition) {
            return items.get(groupPosition).items.size();
        }

        @Override
        public GroupItem getGroup(int groupPosition) {
            return items.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return items.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            GroupHolder holder;
            GroupItem item = getGroup(groupPosition);
            if (convertView == null) {
                holder = new GroupHolder();
                convertView = inflater.inflate(R.layout.main_item_group, parent, false);
                holder.title = (TextView) convertView.findViewById(R.id.textGroup);
                convertView.setTag(holder);
            } else {
                holder = (GroupHolder) convertView.getTag();
            }

            holder.title.setText(item.title);

            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public boolean isChildSelectable(int arg0, int arg1) {
            return true;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent = new Intent(getApplicationContext(), UserInfoActivity.class);
        startActivityForResult(intent,REQUEST_CODE_USERINFO);
        return true;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode == RESULT_OK){
            if (requestCode == REQUEST_CODE_ADD_PROJECT){
                if (data != null){
                    Toast.makeText(getApplicationContext(),"good",Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}