package com.senecacollege.bookapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Book> books = new ArrayList<>(Arrays.asList(
            new Book ("Title 1", "Author 1"),
            new Book ("Title 2", "Author 2"),
            new Book ("Title 3", "Author 3"),
            new Book ("Title 4", "Author 4"),
            new Book ("Title 5", "Author 5"),
            new Book ("Title 6", "Author 6"),
            new Book ("Title 7", "Author 7"),
            new Book ("Title 8", "Author 8"),
            new Book ("Title 9", "Author 9"),
            new Book ("Title 10", "Author 10"),
            new Book ("Title 11", "Author 11"),
            new Book ("Title 12", "Author 12"),
            new Book ("Title 13", "Author 13"),
            new Book ("Title 14", "Author 14"),
            new Book ("Title 15", "Author 15"),
            new Book ("Title 16", "Author 16"),
            new Book ("Title 17", "Author 17"),
            new Book ("Title 18", "Author 18"),
            new Book ("Title 19", "Author 19"),
            new Book ("Title 20", "Author 20")
    ));
    RecyclerView recyclerView;
    RecyclerAdapter adapter = new RecyclerAdapter(books);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.add_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddBookDialog();
            }
        });
    }

    private void showAddBookDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add a new book");

        final EditText inputTitle = new EditText(this);
        inputTitle.setHint("Title");
        final EditText inputAuthor = new EditText(this);
        inputAuthor.setHint("Author");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(20, 10, 20, 10);
        layout.addView(inputTitle);
        layout.addView(inputAuthor);

        builder.setView(layout);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String title = inputTitle.getText().toString().trim();
                String author = inputAuthor.getText().toString().trim();

                if (!title.isEmpty() && !author.isEmpty()){
                    Book newBook = new Book(title, author);
                    books.add(newBook);
                    adapter.notifyItemInserted(books.size() - 1);
                    recyclerView.scrollToPosition(books.size() - 1);
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
}