package com.example.expense_tracker;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText amountEditText, descriptionEditText;
    private Button addButton;
    private ListView entryListView;
    private List<ExpenseEntry> expenseList;
    private ArrayAdapter<ExpenseEntry> adapter;
    private TextView totalExpensesTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountEditText = findViewById(R.id.amountEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        addButton = findViewById(R.id.addButton);
        entryListView = findViewById(R.id.entryListView);
        totalExpensesTextView = findViewById(R.id.totalExpensesTextView);

        expenseList = new ArrayList<>();
        adapter = new ArrayAdapter<ExpenseEntry>(this, R.layout.list_item_layout, expenseList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_item_layout, parent, false);
                }

                ExpenseEntry entry = getItem(position);

                TextView textView = convertView.findViewById(R.id.listItemTextView);
                ImageButton deleteButton = convertView.findViewById(R.id.deleteButton);

                if (entry != null) {
                    textView.setText(entry.toString());

                    deleteButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            expenseList.remove(entry);
                            notifyDataSetChanged();
                            updateTotalExpenses();
                        }
                    });
                }

                return convertView;
            }
        };

        entryListView.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = amountEditText.getText().toString();
                String description = descriptionEditText.getText().toString();

                ExpenseEntry entry = new ExpenseEntry(amount, description);
                expenseList.add(entry);
                adapter.notifyDataSetChanged();
                updateTotalExpenses();

                amountEditText.setText("");
                descriptionEditText.setText("");
            }
        });
    }

    private void updateTotalExpenses() {
        double total = 0;
        for (ExpenseEntry entry : expenseList) {
            total += Double.parseDouble(entry.getAmount());
        }

        totalExpensesTextView.setText("Total Expenses: $" + String.format("%.2f", total));
    }
}
