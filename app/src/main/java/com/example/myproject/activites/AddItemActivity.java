package com.example.myproject.activites;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject.R;
import com.example.myproject.model.Item;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;

public class AddItemActivity extends AppCompatActivity {

    private ImageView imageview;
    private Item item;
    private Button saveBtn;
    private Boolean imgEntered;
    private Spinner spinner;
    private TextView nameView,priceView,descrView;
    private ProgressBar progressBar;
    private Boolean Incomplete;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        imageview=findViewById(R.id.img);
        saveBtn=findViewById(R.id.saveBtn);
        nameView=findViewById(R.id.product_name);
        priceView=findViewById(R.id.price);
        descrView=findViewById(R.id.edtInput);
        spinner =findViewById(R.id.spinner);
        progressBar = findViewById(R.id.progress_bar);
        imgEntered=false;
        Incomplete =false;

        storageReference = FirebaseStorage.getInstance().getReference("Items");
        databaseReference = FirebaseDatabase.getInstance().getReference("Items");
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.getOnItemSelectedListener();


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveBtn.setEnabled(false);
                saveBtn.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                Double price=0.0;
                String description="";
                String name="";
                String cat="";
                if(nameView.getText().toString().isEmpty()){
                    nameView.setError("Please enter a product Name");
                    Incomplete =true;
                }else{
                    name=nameView.getText().toString().trim();
                }
                if(priceView.getText().toString().isEmpty()){
                    priceView.setError("Please enter price");
                    Incomplete =true;
                }else {
                    price=Double.parseDouble(priceView.getText().toString());
                }
                if(descrView.getText().toString().isEmpty()){
                    descrView.setError("Please enter description");
                    Incomplete =true;
                }else {
                    description=descrView.getText().toString();
                }
                if(spinner.getSelectedItem().toString().equals("Category")){
                    Toast.makeText(getApplicationContext(),"Please enter Category",Toast.LENGTH_SHORT).show();
                    Incomplete =true;
                }else {
                    cat = spinner.getSelectedItem().toString();
                }
                if(!imgEntered){
                    Toast.makeText(getApplicationContext(),"Please enter an image",Toast.LENGTH_SHORT).show();
                    Incomplete =true;
                }
                if(Incomplete) {
                    enable();
                    return;
                }
                item = new Item();
                item.setCategory(cat);
                item.setDescription(description);
                item.setItemName(name);
                item.setPrice(price);
                //item.setOwnerID();
                upload();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void selectImage(View v) {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto , 1);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        Bitmap b=null;
        if(resultCode == RESULT_OK && requestCode==1){
            imageUri= imageReturnedIntent.getData();
            try {
                b = decodeUri(getApplicationContext(),imageUri,200);
                imageview.setImageBitmap(b);
                imgEntered=true;

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public static Bitmap decodeUri(Context c, Uri uri, final int requiredSize)
            throws FileNotFoundException {
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(c.getContentResolver().openInputStream(uri), null, o);

        int width_tmp = o.outWidth
                , height_tmp = o.outHeight;
        int scale = 1;

        while(true) {
            if(width_tmp / 2 < requiredSize || height_tmp / 2 < requiredSize)
                break;
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(c.getContentResolver().openInputStream(uri), null, o2);
    }

    private void enable(){
        saveBtn.setEnabled(true);
        saveBtn.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        Incomplete =false;
    }
    private String getFileExtension(Uri uri){
        ContentResolver cr=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }
    private void upload(){
        final StorageReference fileReference = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(imageUri));
        fileReference.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(),"Upload Successful",Toast.LENGTH_SHORT).show();
                        fileReference.getDownloadUrl()
                                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        item.setItemImage(uri.toString());
                                        databaseReference.child(databaseReference.push().getKey()).setValue(item);
                                    }
                                });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        enable();
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

            }
        });
    }
}
