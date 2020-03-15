package joel.greenrivertech.net.contactsbook;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class EditContact extends Activity{

    EditText firstName;
    EditText lastName;
    EditText phoneNumber;
    EditText emailAddress;
    EditText homeAddress;
    DBTools queries = new DBTools(this);

    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_contact);
        firstName = (EditText) findViewById(R.id.firstNameEditText);
        lastName = (EditText) findViewById(R.id.lastNameEditText);
        phoneNumber = (EditText) findViewById(R.id.phoneNumberEditText);
        emailAddress = (EditText) findViewById(R.id.emailAddressEditText);
        homeAddress = (EditText) findViewById(R.id.homeAddressEditText);

        Intent theIntent = getIntent();
        String contactId = theIntent.getStringExtra("contactId");
        HashMap<String, String> contactList = queries.getContactInfo(contactId);

        if(contactList.size() != 0){
            firstName.setText(contactList.get("firstName"));
            lastName.setText(contactList.get("lastName"));
            phoneNumber.setText(contactList.get("phoneNumber"));
            emailAddress.setText(contactList.get("emailAddress"));
            homeAddress.setText(contactList.get("homeAddress"));
        }
    }

    public void editContact(View view){

        HashMap<String, String> queryValuesMap = new  HashMap<String, String>();
        firstName = (EditText) findViewById(R.id.firstNameEditText);
        lastName = (EditText) findViewById(R.id.lastNameEditText);
        phoneNumber = (EditText) findViewById(R.id.phoneNumberEditText);
        emailAddress = (EditText) findViewById(R.id.emailAddressEditText);
        homeAddress = (EditText) findViewById(R.id.homeAddressEditText);

        Intent theIntent = getIntent();
        String contactId = theIntent.getStringExtra("contactId");

        queryValuesMap.put("contactId", contactId);
        queryValuesMap.put("firstName", firstName.getText().toString());
        queryValuesMap.put("lastName", lastName.getText().toString());
        queryValuesMap.put("phoneNumber", phoneNumber.getText().toString());
        queryValuesMap.put("emailAddress", emailAddress.getText().toString());
        queryValuesMap.put("homeAddress", homeAddress.getText().toString());

        if(firstName.getText().toString().length() == 0 | lastName.getText().toString().length() == 0){
            Toast.makeText(EditContact.this, "Must have a first and last name.", Toast.LENGTH_SHORT).show();
        }
        else {
            queries.updateContact(queryValuesMap);
            this.callMainActivity(view);
        }
    }

    public void removeContact(View view){
        Intent theIntent = getIntent();
        String contactId = theIntent.getStringExtra("contactId");

        queries.deleteContact(contactId);
        this.callMainActivity(view);
    }

    public void callMainActivity(View view){
        Intent theIntent = new Intent(getApplication(), MainActivity.class);
        startActivity(theIntent);
    }
}
