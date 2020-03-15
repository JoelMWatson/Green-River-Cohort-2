package joel.greenrivertech.net.contactsbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class NewContact extends Activity {

    DBTools queries = new DBTools(this);

    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_contact);
    }

    public void addNewContact(View view){
        EditText firstName = (EditText) findViewById(R.id.firstName);
        EditText lastName = (EditText) findViewById(R.id.lastName);
        EditText phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        EditText emailAddress = (EditText) findViewById(R.id.emailAddress);
        EditText homeAddress = (EditText) findViewById(R.id.homeAddress);

        HashMap<String, String> queryValuesMap = new HashMap<String, String>();

        queryValuesMap.put("firstName", firstName.getText().toString());
        queryValuesMap.put("lastName", lastName.getText().toString());
        queryValuesMap.put("phoneNumber", phoneNumber.getText().toString());
        queryValuesMap.put("emailAddress", emailAddress.getText().toString());
        queryValuesMap.put("homeAddress", homeAddress.getText().toString());

        if(firstName.getText().toString().length() == 0 | lastName.getText().toString().length() == 0){
            Toast.makeText(NewContact.this, "Must have a first and last name.", Toast.LENGTH_SHORT).show();
        }
        else {
            queries.insertContact(queryValuesMap);

            this.callMainActivity(view);
        }
    }

    public void callMainActivity(View view){
        Intent theIntent = new Intent(getApplication(), MainActivity.class);
        startActivity(theIntent);
    }

}
