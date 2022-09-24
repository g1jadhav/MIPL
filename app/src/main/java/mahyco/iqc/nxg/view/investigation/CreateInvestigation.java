package mahyco.iqc.nxg.view.investigation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.AbstractThreadedSyncAdapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.daasuu.mp4compose.FillMode;
import com.daasuu.mp4compose.Rotation;
import com.daasuu.mp4compose.composer.Mp4Composer;
import com.daasuu.mp4compose.filter.GlFilterGroup;
import com.daasuu.mp4compose.filter.GlMonochromeFilter;
import com.daasuu.mp4compose.filter.GlVignetteFilter;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.enums.EPickType;
import com.vansuita.pickimage.listeners.IPickCancel;
import com.vansuita.pickimage.listeners.IPickResult;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.zip.DeflaterOutputStream;

import mahyco.iqc.nxg.R;
import mahyco.iqc.nxg.model.ActionModel;
import mahyco.iqc.nxg.model.CheckPoint;
import mahyco.iqc.nxg.model.CheckPointModel;
import mahyco.iqc.nxg.model.ContractPlantModel;
import mahyco.iqc.nxg.model.CropModel;
import mahyco.iqc.nxg.model.FileModel;
import mahyco.iqc.nxg.model.HybridModel;
import mahyco.iqc.nxg.model.NCModel;
import mahyco.iqc.nxg.model.NCTypeModel;
import mahyco.iqc.nxg.model.ObservationResponseModel;
import mahyco.iqc.nxg.model.ObservationforNCTypeModel;
import mahyco.iqc.nxg.model.OwnerModel;
import mahyco.iqc.nxg.model.ProcessModel;
import mahyco.iqc.nxg.model.UploadImageModel;
import mahyco.iqc.nxg.util.Constants;
import mahyco.iqc.nxg.util.MultipartUtility;
import mahyco.iqc.nxg.util.MyApplicationUtil;
import mahyco.iqc.nxg.util.Preferences;
import mahyco.iqc.nxg.util.RetrofitClient;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateInvestigation extends AppCompatActivity implements CreateInvestigationListener, IPickResult {
    EditText et_inspectiondate, et_remark, et_batchno, et_observation;
    SearchableSpinner sp_process, sp_crop, sp_hybrid, sp_checkpoint, sp_nctype, sp_owner, sp_contractplant;
    String process[] = {"Process 1", "Process 2", "Process 3", "Process 4", "Process 5", "Process 6", "Process 7"};
    String crop[] = {"Crop 1", "Crop 2", "Crop 3", "Crop 4", "Crop 5", "Crop 6", "Crop 7"};
    String hybrid[] = {"Hybrid 1", "Hybrid 2", "Hybrid 3", "Hybrid 4", "Hybrid 5", "Hybrid 6", "Hybrid 7"};
    String checkpoint[] = {"Check Point 1", "Check Point 2", "Check Point 3", "Check Point 4", "Check Point 5", "Check Point 6", "Check Point 7"};
    String nctype[] = {"NC Type 1", "NC Type 2", "NC Type 3", "NC Type 4", "NC Type 5", "NC Type 6", "NC Type 7"};
    String owner[] = {"Owner 1", "Owner 2", "Owner 3", "Owner 4", "Owner 5", "Owner 6", "Owner 7"};
    int selected_process, selected_crop, selected_hybrid, selected_nctype, selected_owner;
    ArrayAdapter adapter_process, adapter_crop, adapter_hybrid, adapter_checkpoint, adapter_nctype, adapter_owner, adapter_contractplant;
    int mYear, mMonth, mDay;
    String selected_checkpoint;
    CreateInvestigationAPI createInvestigationAPI;
    Context context;
    JsonObject json_contractplant, jsonObject_process, jsonObject_crop, jsonObject_checkpoint, jsonObject_hybrid, jsonObject_nctype, jsonObject_owner;
    int slectedType = 0;
    String imagefilename = "", videofilename = "", masterimagepath = "";
    LinearLayout ll;
    Switch switch_isNCType;
    TextView txt_img_nctype, txt_video_nctype, txt_img;
    ProgressDialog progressDialog;
    int selectedPlantID;
    JsonArray uploadImageModels = new JsonArray();
    String selectedDate = "";
    HashSet set_checkpoint_id;
    HashSet set_checkpoint_title;
    ArrayList<FileModel> files;
    LinearLayout ll_checkpoint;
    Button btnsubmit;
    int iqcplant;
    int selected_contractplant;
    LinearLayout ll_conntractplant;
    List<CheckPoint> lst_checkpointslist;
    Dialog dialog_checkpoints, dialog_checkpointimages;
    Button btn_checkpoint;
   // String str_chk_img1, str_chk_img2, str_chk_img3, str_chk_img4, str_chk_img5;
    String str_chk_img1 = "", str_chk_img2 = "", str_chk_img3 = "", str_chk_img4 = "", str_chk_img5 = "";
    TextView txt_uploadSuccess1, txt_uploadSuccess2, txt_uploadSuccess3, txt_uploadSuccess4, txt_uploadSuccess5;

    LinearLayout ll_remark;
    int uploadstatus = 0;
    boolean isNCEnabled=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_investigation);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        context = CreateInvestigation.this;
        setTitle("New Investigation");
        try {
            //   Toast.makeText(context, ""+Preferences.get(context, Preferences.SELECTEIQCPLANT).toString().trim(), Toast.LENGTH_SHORT).show();
            iqcplant = Integer.parseInt(Preferences.get(context, Preferences.SELECTEIQCPLANT).toString().trim());
        } catch (NumberFormatException e) {
            iqcplant = 0;
        }
        et_inspectiondate = findViewById(R.id.et_investigationdate);
        Calendar c = Calendar.getInstance();

        Log.v("hari", "CurrentTime:" + c.getTime());

        SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate1 = df1.format(c.getTime());
        String formattedDate12 = df2.format(c.getTime());
        et_inspectiondate.setText(formattedDate1);
        selectedDate = formattedDate12;

        et_remark = findViewById(R.id.et_remark);
        et_batchno = findViewById(R.id.et_batchno);
        et_observation = findViewById(R.id.et_observation);
        sp_process = findViewById(R.id.spinner_process);
        sp_crop = findViewById(R.id.spinner_crop);
        sp_hybrid = findViewById(R.id.spinner_hybrid);
        sp_checkpoint = findViewById(R.id.spinner_checkpoint);
        sp_nctype = findViewById(R.id.spinner_nc_type);
        sp_owner = findViewById(R.id.spinner_owner);

        sp_process.setTitle("Select Process");
        sp_crop.setTitle("Select Crop");
        sp_hybrid.setTitle("Select Hybrid");
        sp_checkpoint.setTitle("Select Checkpoint");
        sp_nctype.setTitle("Select NC Type");
        sp_owner.setTitle("Select Activity Owner");


        switch_isNCType = findViewById(R.id.switch_isinctype);
        sp_contractplant = findViewById(R.id.spinner_contractplant);
        ll = findViewById(R.id.ll);
        ll_checkpoint = findViewById(R.id.ll_checkpoint);
        ll_remark = findViewById(R.id.ll_remark);
        ll_remark.setVisibility(View.GONE);
        txt_img_nctype = findViewById(R.id.txt_img_nctype);
        txt_video_nctype = findViewById(R.id.txt_video_nctype);
        txt_img = findViewById(R.id.txt_img);
        btnsubmit = findViewById(R.id.btn_submit);
        btn_checkpoint = findViewById(R.id.btn_checkpoint);
        ll_conntractplant = findViewById(R.id.ll_contractplant);

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Uploading Image...");

        adapter_process = new ArrayAdapter(CreateInvestigation.this, android.R.layout.simple_list_item_1, process);
        adapter_crop = new ArrayAdapter(CreateInvestigation.this, android.R.layout.simple_list_item_1, crop);
        adapter_hybrid = new ArrayAdapter(CreateInvestigation.this, android.R.layout.simple_list_item_1, hybrid);
        adapter_checkpoint = new ArrayAdapter(CreateInvestigation.this, android.R.layout.simple_list_item_1, checkpoint);
        adapter_nctype = new ArrayAdapter(CreateInvestigation.this, android.R.layout.simple_list_item_1, nctype);
        adapter_owner = new ArrayAdapter(CreateInvestigation.this, android.R.layout.simple_list_item_1, owner);
        adapter_contractplant = new ArrayAdapter(CreateInvestigation.this, android.R.layout.simple_list_item_1, owner);

        sp_process.setAdapter(adapter_process);
        sp_crop.setAdapter(adapter_crop);
        sp_hybrid.setAdapter(adapter_hybrid);
        sp_checkpoint.setAdapter(adapter_checkpoint);
        sp_nctype.setAdapter(adapter_nctype);
        sp_owner.setAdapter(adapter_owner);
        sp_contractplant.setAdapter(adapter_contractplant);
        set_checkpoint_id = new HashSet();
        set_checkpoint_title = new HashSet();

        selectedPlantID = Integer.parseInt(Preferences.get(context, Preferences.SELECTEIQCPLANT).trim());
        files = new ArrayList<>();

        et_inspectiondate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();
                mYear = mcurrentDate.get(Calendar.YEAR);
                mMonth = mcurrentDate.get(Calendar.MONTH);
                mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                final DatePickerDialog mDatePicker = new DatePickerDialog(CreateInvestigation.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        /*      Your code   to get date and time    */

                        String ssm = "", ssd = "";
                        if ((selectedmonth + 1) < 10)
                            ssm = "0" + (selectedmonth + 1);
                        else
                            ssm = "" + (selectedmonth + 1);
                        if ((selectedday) < 10)
                            ssd = "0" + selectedday;
                        else
                            ssd = "" + selectedday;

                        //  String dd = selectedyear + "/" + (ssm) + "/" + ssd;
                        String dd = ssd + "/" + (ssm) + "/" + selectedyear;
                        et_inspectiondate.setText(dd);
                        selectedDate = selectedyear + "-" + (ssm) + "-" + ssd;

                    }
                }, mYear, mMonth, mDay);
                //mDatePicker.getDatePicker().setMaxDate(System.currentTimeMillis());
                mDatePicker.setTitle(" Inspection Date ");
                mDatePicker.show();

            }
        });

        createInvestigationAPI = new CreateInvestigationAPI(context, this);

        jsonObject_process = new JsonObject();
        jsonObject_process.addProperty("filterValue", iqcplant);
        jsonObject_process.addProperty("FilterOption", "IQCPlantId");
        createInvestigationAPI.getProcessList(jsonObject_process);

        jsonObject_crop = new JsonObject();
        jsonObject_crop.addProperty("filterValue", 1);
        jsonObject_crop.addProperty("FilterOption", "CropId");
        createInvestigationAPI.getCropList(jsonObject_crop);
        //iqcplant=2;
        if (iqcplant == 4) {
            ll_conntractplant.setVisibility(View.VISIBLE);
            json_contractplant = new JsonObject();
            json_contractplant.addProperty("filterValue", iqcplant);
            json_contractplant.addProperty("FilterOption", "IQCPlant");
            createInvestigationAPI.getContractPlantList(jsonObject_crop);
        } else {
            ll_conntractplant.setVisibility(View.GONE);
            String strgetOwnerParam = "";
            if (iqcplant != 4)
                strgetOwnerParam = iqcplant + "-0";

            jsonObject_owner = new JsonObject();
            jsonObject_owner.addProperty("filterValue", strgetOwnerParam);
            jsonObject_owner.addProperty("FilterOption", "GetByIQCPlant_ContractPlan");
            createInvestigationAPI.getOwnerList(jsonObject_owner);

        }
        /*
        jsonObject_checkpoint = new JsonObject();
        jsonObject_checkpoint.addProperty("filterValue", 1);
        jsonObject_checkpoint.addProperty("FilterOption", "ProcessId");
        createInvestigationAPI.getCheckPointList(jsonObject_checkpoint);
     */

/*      jsonObject_nctype = new JsonObject();
        jsonObject_nctype.addProperty("filterValue", "1,2-1");
        jsonObject_nctype.addProperty("FilterOption", "GetByCheckPoint_Process");
        createInvestigationAPI.getNCType(jsonObject_nctype);
*/


        switch_isNCType.setChecked(true);
        switch_isNCType.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    ll.setVisibility(View.VISIBLE);
                    ll_remark.setVisibility(View.GONE);
                    isNCEnabled=b;
                } else {
                    ll.setVisibility(View.GONE);
                    ll_remark.setVisibility(View.VISIBLE);
                    isNCEnabled=b;
                }
            }
        });
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    if (et_inspectiondate.getText().toString().trim().equals("")) {
                        et_inspectiondate.setError("Missing Date");
                    } else if (et_batchno.getText().toString().trim().equals("")) {
                        et_batchno.setError("Missing Batch Number");
                    } else if (set_checkpoint_id.size() < 1) {
                        Toast.makeText(context, "Please choose Checkpoint", Toast.LENGTH_SHORT).show();
                    } else if (selected_process < 0) {
                        Toast.makeText(context, "Please choose process", Toast.LENGTH_SHORT).show();
                    } else {

                        JsonArray checkPointModels = new JsonArray();
                        Iterator<Integer> i = set_checkpoint_id.iterator();

                        // Holds true till there is single element remaining
                        while (i.hasNext()) {
                            JsonObject jsonObjectcheckpoint = new JsonObject();
                            jsonObjectcheckpoint.addProperty("CheckPointId", i.next());
                            checkPointModels.add(jsonObjectcheckpoint);
                        }


                        JsonArray nCTypeModels = new JsonArray();
                        JsonObject jsonObjectnCTypeModels = new JsonObject();
                        jsonObjectnCTypeModels.addProperty("NCTypeId", selected_nctype);
                        nCTypeModels.add(jsonObjectnCTypeModels);


                        JsonArray observationforNCTypeModels = new JsonArray();
                        JsonObject jsonObjectobservationforNCTypeModels = new JsonObject();
                        jsonObjectobservationforNCTypeModels.addProperty("Observation", et_observation.getText().toString());
                        observationforNCTypeModels.add(jsonObjectobservationforNCTypeModels);


                        JsonObject jsonObject = new JsonObject();


                        jsonObject.addProperty("CreatedDt", selectedDate);
                        jsonObject.addProperty("PlantId", selectedPlantID);
                        jsonObject.addProperty("ActivityOwnerId", selected_owner);
                        jsonObject.addProperty("ProcessId", selected_process);
                        jsonObject.addProperty("CropId", selected_crop);
                        jsonObject.addProperty("HybridId", selected_hybrid);
                        jsonObject.addProperty("BatchNo", et_batchno.getText().toString());
                        jsonObject.addProperty("IsHavingNCType", isNCEnabled);
                        jsonObject.addProperty("Remark", et_remark.getText().toString().trim());
                        jsonObject.addProperty("CreatedBy", "" + Preferences.get(context, Preferences.USER_ID).toString().trim());
                        jsonObject.add("checkPointModels", checkPointModels);
                        jsonObject.add("nCTypeModels", nCTypeModels);
                        jsonObject.add("uploadImageModels", uploadImageModels);
                        jsonObject.add("observationforNCTypeModels", observationforNCTypeModels);


                        createInvestigationAPI.createObservation(jsonObject);

                        Log.i("Json Object ", "" + jsonObject.toString());
                    }
                } catch (Exception e) {
                    Log.i("Error is ", e.getMessage());
                }
            }
        });


        btn_checkpoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    dialog_checkpoints = new Dialog(context);
                    dialog_checkpoints.setContentView(R.layout.popup_checkpointlist);
                    dialog_checkpoints.setCanceledOnTouchOutside(false);
                    ImageView img_close = dialog_checkpoints.findViewById(R.id.img_close);
                    img_close.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog_checkpoints.dismiss();
                        }
                    });
                    LinearLayout linearLayout = dialog_checkpoints.findViewById(R.id.ll_popup_checkpoints);
                    for (CheckPoint c : lst_checkpointslist) {
                        CheckBox checkBox = new CheckBox(context);
                        checkBox.setText("" + c.getName());
                        if (c.isRequired()) {
                            checkBox.setChecked(true);
                            checkBox.setEnabled(false);
                            set_checkpoint_id.add(c.getId());
                            set_checkpoint_title.add(c.getName());
                        }
                        //  checkBox.setText(""+c.getId());
                        linearLayout.addView(checkBox);
                        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if (b) {
                                    set_checkpoint_id.add(c.getId());
                                    set_checkpoint_title.add(c.getName());
                                    showCheckPointImageDialog(c.getId(), c.getName());
                                } else {
                                    set_checkpoint_id.remove(c.getId());
                                    set_checkpoint_title.remove(c.getName());
                                }
                                Log.i("added_id", set_checkpoint_id.toString().replace("[", "").replace("]", ""));
                                Log.i("addedtitle", set_checkpoint_title.toString());
                                selected_checkpoint = set_checkpoint_id.toString().replace("[", "").replace("]", "") + "-" + selected_process;
                                jsonObject_nctype = new JsonObject();
                                jsonObject_nctype.addProperty("filterValue", selected_checkpoint);
                                jsonObject_nctype.addProperty("FilterOption", "GetByCheckPoint_Process");
                                createInvestigationAPI.getNCType(jsonObject_nctype);
                            }
                        });
                    }


                    dialog_checkpoints.show();


                } catch (Exception e) {
                    Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void showCheckPointImageDialog(int id, String name) {
        try {
            ImageView imageView1, imageView2, imageView3, imageView4, imageView5;
            Button upload1, upload2, upload3, upload4, upload5;
            TextView txt_title;
            dialog_checkpointimages = new Dialog(context);

            dialog_checkpointimages.setCanceledOnTouchOutside(false);
            dialog_checkpointimages.setContentView(R.layout.popup_checkpointlist_images);

            ImageView img_close = dialog_checkpointimages.findViewById(R.id.img_close);
            img_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog_checkpointimages.dismiss();
                }
            });
            txt_title=dialog_checkpointimages.findViewById(R.id.txt_title);
            txt_title.setText(" "+name);
            imageView1 = dialog_checkpointimages.findViewById(R.id.imageView1);
            imageView2 = dialog_checkpointimages.findViewById(R.id.imageView2);
            imageView3 = dialog_checkpointimages.findViewById(R.id.imageView3);
            imageView4 = dialog_checkpointimages.findViewById(R.id.imageView4);
            imageView5 = dialog_checkpointimages.findViewById(R.id.imageView5);
            upload1 = dialog_checkpointimages.findViewById(R.id.button1_upload);
            upload2 = dialog_checkpointimages.findViewById(R.id.button2_upload);
            upload3 = dialog_checkpointimages.findViewById(R.id.button3_upload);
            upload4 = dialog_checkpointimages.findViewById(R.id.button4_upload);
            upload5 = dialog_checkpointimages.findViewById(R.id.button5_upload);
            txt_uploadSuccess1 = dialog_checkpointimages.findViewById(R.id.txt_success1);
            txt_uploadSuccess2 = dialog_checkpointimages.findViewById(R.id.txt_success2);
            txt_uploadSuccess3 = dialog_checkpointimages.findViewById(R.id.txt_success3);
            txt_uploadSuccess4 = dialog_checkpointimages.findViewById(R.id.txt_success4);
            txt_uploadSuccess5 = dialog_checkpointimages.findViewById(R.id.txt_success5);

            imageView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PickImageDialog.build(new PickSetup())
                            .setOnPickResult(new IPickResult() {
                                @Override
                                public void onPickResult(PickResult r) {
                                    //TODO: do what you have to...
                                    imageView1.setImageBitmap(r.getBitmap());
                                    str_chk_img1 = r.getPath();
                                    uploadstatus = 11;
                                }
                            })
                            .setOnPickCancel(new IPickCancel() {
                                @Override
                                public void onCancelClick() {
                                    //TODO: do what you have to if user clicked cancel
                                }
                            }).show(getSupportFragmentManager());
                }
            });
            imageView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PickImageDialog.build(new PickSetup())
                            .setOnPickResult(new IPickResult() {
                                @Override
                                public void onPickResult(PickResult r) {
                                    //TODO: do what you have to...
                                    imageView2.setImageBitmap(r.getBitmap());
                                    str_chk_img2 = r.getPath();
                                    uploadstatus = 22;
                                }
                            })
                            .setOnPickCancel(new IPickCancel() {
                                @Override
                                public void onCancelClick() {
                                    //TODO: do what you have to if user clicked cancel
                                }
                            }).show(getSupportFragmentManager());
                }
            });
            imageView3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PickImageDialog.build(new PickSetup())
                            .setOnPickResult(new IPickResult() {
                                @Override
                                public void onPickResult(PickResult r) {
                                    //TODO: do what you have to...
                                    imageView3.setImageBitmap(r.getBitmap());
                                    str_chk_img3 = r.getPath();
                                    uploadstatus = 33;
                                }
                            })
                            .setOnPickCancel(new IPickCancel() {
                                @Override
                                public void onCancelClick() {
                                    //TODO: do what you have to if user clicked cancel
                                }
                            }).show(getSupportFragmentManager());
                }
            });
            imageView4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PickImageDialog.build(new PickSetup())
                            .setOnPickResult(new IPickResult() {
                                @Override
                                public void onPickResult(PickResult r) {
                                    //TODO: do what you have to...
                                    imageView4.setImageBitmap(r.getBitmap());
                                    str_chk_img4 = r.getPath();
                                    uploadstatus = 44;
                                }
                            })
                            .setOnPickCancel(new IPickCancel() {
                                @Override
                                public void onCancelClick() {
                                    //TODO: do what you have to if user clicked cancel
                                }
                            }).show(getSupportFragmentManager());
                }
            });
            imageView5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PickImageDialog.build(new PickSetup())
                            .setOnPickResult(new IPickResult() {
                                @Override
                                public void onPickResult(PickResult r) {
                                    //TODO: do what you have to...
                                    imageView5.setImageBitmap(r.getBitmap());
                                    str_chk_img5 = r.getPath();
                                    uploadstatus = 55;
                                }
                            })
                            .setOnPickCancel(new IPickCancel() {
                                @Override
                                public void onCancelClick() {
                                    //TODO: do what you have to if user clicked cancel
                                }
                            }).show(getSupportFragmentManager());
                }
            });


            upload1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (str_chk_img1.trim().equals("")) {
                        Toast.makeText(context, "Please capture the file.", Toast.LENGTH_SHORT).show();
                    } else {
                        slectedType = 4;
                        new UploadFile().execute(str_chk_img1, "" + id);

                    }
                }
            });
            upload2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (str_chk_img2.trim().equals("")) {
                        Toast.makeText(context, "Please capture the file.", Toast.LENGTH_SHORT).show();
                    } else {
                        slectedType = 4;
                        new UploadFile().execute(str_chk_img2, "" + id);

                    }
                }
            });
            upload3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (str_chk_img3.trim().equals("")) {
                        Toast.makeText(context, "Please capture the file.", Toast.LENGTH_SHORT).show();
                    } else {
                        slectedType = 4;
                        new UploadFile().execute(str_chk_img3, "" + id);

                    }
                }
            });
            upload4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (str_chk_img4.trim().equals("")) {
                        Toast.makeText(context, "Please capture the file.", Toast.LENGTH_SHORT).show();
                    } else {
                        slectedType = 4;
                        new UploadFile().execute(str_chk_img4, "" + id);

                    }
                }
            });
            upload5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (str_chk_img5.trim().equals("")) {
                        Toast.makeText(context, "Please capture the file.", Toast.LENGTH_SHORT).show();
                    } else {
                        slectedType = 4;
                        new UploadFile().execute(str_chk_img5, "" + id);

                    }
                }
            });
            dialog_checkpointimages.show();

        } catch (Exception e) {
            Toast.makeText(context, "Error is " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // app icon in action bar clicked; goto parent activity.
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResult(String result) {

    }

    @Override
    public void onListResponce(List result) {

    }

    @Override
    public void onListResponce_ProcessList(List<ProcessModel> result) {
        try {

            ArrayAdapter userAdapter = new ArrayAdapter(this, R.layout.spinner, result);
            sp_process.setAdapter(userAdapter);
            sp_process.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // Get the value selected by the user
                    // e.g. to store it as a field or immediately call a method
                    ProcessModel user = (ProcessModel) parent.getSelectedItem();
                    selected_process = user.getId();
                    jsonObject_checkpoint = new JsonObject();
                    jsonObject_checkpoint.addProperty("filterValue", selected_process);
                    jsonObject_checkpoint.addProperty("FilterOption", "ProcessId");
                    createInvestigationAPI.getCheckPointList(jsonObject_checkpoint);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

        } catch (Exception e) {

        }
    }

    @Override
    public void onListResponce(ActionModel result) {

    }

    @Override
    public void onListResponce_CropList(List<CropModel> result) {
        try {

            ArrayAdapter userAdapter = new ArrayAdapter(this, R.layout.spinner, result);
            sp_crop.setAdapter(userAdapter);
            sp_crop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // Get the value selected by the user
                    // e.g. to store it as a field or immediately call a method
                    CropModel user = (CropModel) parent.getSelectedItem();
                    //  Toast.makeText(context, "" + user.getName(), Toast.LENGTH_SHORT).show();
                    selected_crop = user.getId();
                    jsonObject_hybrid = new JsonObject();
                    jsonObject_hybrid.addProperty("filterValue", user.getId());
                    jsonObject_hybrid.addProperty("FilterOption", "CropId");
                    createInvestigationAPI.getHybridList(jsonObject_hybrid);

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

        } catch (Exception e) {

        }
    }

    @Override
    public void onListResponce_HybridList(List<HybridModel> result) {
        try {

            ArrayAdapter userAdapter = new ArrayAdapter(this, R.layout.spinner, result);
            sp_hybrid.setAdapter(userAdapter);
            sp_hybrid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    HybridModel user = (HybridModel) parent.getSelectedItem();
                    selected_hybrid = user.getId();
                    //Toast.makeText(context, "" + user.getName(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

        } catch (Exception e) {

        }
    }

    @Override
    public void onListResponce_CheckPointList(List<CheckPoint> result) {
        try {
            set_checkpoint_id = new HashSet();
            set_checkpoint_title = new HashSet();
            ll_checkpoint.removeAllViews();
            lst_checkpointslist = new ArrayList(result);
           /* for(CheckPoint c:result)
            {
                CheckBox checkBox=new CheckBox(context);
                checkBox.setText(""+c.getName());
                if(c.isRequired()) {
                    checkBox.setChecked(true);
                checkBox.setEnabled(false);
                    set_checkpoint_id.add(c.getId());
                    set_checkpoint_title.add(c.getName());
                }
              //  checkBox.setText(""+c.getId());
                ll_checkpoint.addView(checkBox);
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if(b)
                        {
                            set_checkpoint_id.add(c.getId());
                            set_checkpoint_title.add(c.getName());
                        }else
                        {
                            set_checkpoint_id.remove(c.getId());
                            set_checkpoint_title.remove(c.getName());
                        }
                        Log.i("added_id",set_checkpoint_id.toString().replace("[","").replace("]",""));
                        Log.i("addedtitle",set_checkpoint_title.toString());
                        selected_checkpoint=set_checkpoint_id.toString().replace("[","").replace("]","")+"-"+selected_process;
                        jsonObject_nctype = new JsonObject();
                        jsonObject_nctype.addProperty("filterValue", selected_checkpoint);
                        jsonObject_nctype.addProperty("FilterOption", "GetByCheckPoint_Process");
                        createInvestigationAPI.getNCType(jsonObject_nctype);
                    }
                });
            }*/


            ArrayAdapter userAdapter = new ArrayAdapter(this, R.layout.spinner, result);
            sp_checkpoint.setAdapter(userAdapter);
            sp_checkpoint.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    CheckPoint user = (CheckPoint) parent.getSelectedItem();
                    //  selected_checkpoint = user.getId();


                    // Toast.makeText(context, "" + user.getName(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

        } catch (Exception e) {

        }
    }

    @Override
    public void onListResponce_NCTypeList(List<NCTypeModel> result) {
        try {

            ArrayAdapter userAdapter = new ArrayAdapter(this, R.layout.spinner, result);
            sp_nctype.setAdapter(userAdapter);
            sp_nctype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    NCTypeModel user = (NCTypeModel) parent.getSelectedItem();
                    selected_nctype = user.getId();
                    // Toast.makeText(context, "" + user.getName(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

        } catch (Exception e) {

        }
    }

    @Override
    public void onListResponce_OwnerList(List<OwnerModel> result) {
        try {

            ArrayAdapter userAdapter = new ArrayAdapter(this, R.layout.spinner, result);
            sp_owner.setTitle("Select Activity Owner");
            sp_owner.setAdapter(userAdapter);
            sp_owner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    OwnerModel user = (OwnerModel) parent.getSelectedItem();
                    selected_owner = user.getActivityOwnerId();
                    //Toast.makeText(context, "" + user.getActivityOwner(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

        } catch (Exception e) {

        }
    }

    @Override
    public void onObservationResponse(ObservationResponseModel result) {
        try {
            if (result != null) {
                if (result.isResultFlag()) {
                    Toast.makeText(context, "Observation status : " + result.getStatus(), Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(context, "Error :" + result.getComment(), Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void onListResponce_ContactPlant(List<ContractPlantModel> result) {
        try {

            ArrayAdapter userAdapter = new ArrayAdapter(this, R.layout.spinner, result);
            sp_contractplant.setAdapter(userAdapter);
            sp_contractplant.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ContractPlantModel user = (ContractPlantModel) parent.getSelectedItem();
                    selected_contractplant = user.getId();
                    //   Toast.makeText(context, "" + user.getId()+user.getName(), Toast.LENGTH_SHORT).show();

                    String strgetOwnerParam = "";
                    if (iqcplant == 4)
                        strgetOwnerParam = iqcplant + "-" + user.getId();
                    {

                        //  Toast.makeText(context, "" + strgetOwnerParam, Toast.LENGTH_SHORT).show();

                        jsonObject_owner = new JsonObject();
                        jsonObject_owner.addProperty("filterValue", strgetOwnerParam);
                        jsonObject_owner.addProperty("FilterOption", "GetByIQCPlant_ContractPlan");
                        createInvestigationAPI.getOwnerList(jsonObject_owner);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

        } catch (Exception e) {

        }
    }

    public void capture_image(View view) {
        try {
            slectedType = 1; // Selected type is Image
            PickImageDialog.build(new PickSetup()
                    .setPickTypes(EPickType.CAMERA))
                    .show(CreateInvestigation.this);

        } catch (Exception e) {

        }
    }

    public void capture_video(View view) {
        try {

            try {
                slectedType = 2;//Selected Type is Video
                PickImageDialog.build(new PickSetup().setVideo(true)
                        .setPickTypes(EPickType.CAMERA))
                        .show(CreateInvestigation.this);

            } catch (Exception e) {

            }

        } catch (Exception e) {

        }
    }

    public void imagecapture(View view) {
        try {

            try {
                slectedType = 3;//Selected Type is Video
                PickImageDialog.build(new PickSetup()
                        .setPickTypes(EPickType.CAMERA))
                        .show(CreateInvestigation.this);

            } catch (Exception e) {

            }

        } catch (Exception e) {

        }
    }

    @Override
    public void onPickResult(PickResult r) {
        try {
            File file = new File(r.getPath());
            FileModel fileModel = new FileModel();
            fileModel.setFile(file);
            if (slectedType == 2)
                fileModel.setType("Video");
            else
                fileModel.setType("Image");
            files.add(fileModel);
            int file_size = Integer.parseInt(String.valueOf((file.length() / 1024) / 1024));
            if (slectedType == 1) {
                imagefilename = r.getPath();
                txt_img_nctype.setText(r.getPath() + "\n  File Size : " + file_size + " Mb");
            } else if (slectedType == 2) {
                videofilename = r.getPath();
                txt_video_nctype.setText(r.getPath() + "\n  File Size : " + file_size + " Mb");
            } else if (slectedType == 3) {
                masterimagepath = r.getPath();
                txt_img.setText(r.getPath() + "\n  File Size : " + file_size + " Mb");
            }

            // Toast.makeText(context, slectedType + "  " + r.getPath(), Toast.LENGTH_SHORT).show();

        } catch (Exception e) {

        }
    }

    private void uploadFile(String path, String type) {
        if (type.equals("2")) {
            String outputPath = "";
            try {
                String inputPath = path;
                String originalpath = path.substring(0, path.lastIndexOf("/") + 1);
                String ff = originalpath + "MyFile.mp4";
                outputPath = ff;

                Log.i("Path", inputPath + "  and " + outputPath);
                new Mp4Composer(inputPath, outputPath)
                        .timeScale(0.2f)
                        .rotation(Rotation.ROTATION_90)
                        .fillMode(FillMode.PRESERVE_ASPECT_FIT)
                        .filter(new GlFilterGroup(new GlMonochromeFilter(), new GlVignetteFilter()))
                        .listener(new Mp4Composer.Listener() {
                            @Override
                            public void onProgress(double progress) {
                                Log.d("-->", "onProgress = " + progress);
                            }

                            @Override
                            public void onCurrentWrittenVideoTime(long timeUs) {

                            }

                            @Override
                            public void onCompleted() {
                                Log.d("TAG", "onCompleted()");
                                uploadFileVideo(ff);

                            }

                            @Override
                            public void onCanceled() {
                                Log.d("TAG", "onCanceled");
                            }

                            @Override
                            public void onFailed(Exception exception) {
                                Log.e("TAG", "onFailed()", exception);
                            }
                        })
                        .start();


            } catch (Exception e) {
                Log.i("Compress Done error", e.getMessage());
            }

        } else {
            uploadFileVideo(path);
        }


    }

    public class UploadFile extends AsyncTask {
        ProgressDialog progressDialog;
        int checkpointid = 0;

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            progressDialog.dismiss();
            try {
                List<String> response = (List) o;
                Log.v("rht", "SERVER REPLIED:");
                for (String line : response) {
                    Log.v("rht", "Line : " + line);
                    try {
                        JSONArray jsonArray = new JSONArray(line);
                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            JsonObject jsonObjectuploadImageModels = new JsonObject();
                            jsonObjectuploadImageModels.addProperty("FileType", jsonObject.getString("FileType"));
                            jsonObjectuploadImageModels.addProperty("InspectionCheckPointId", checkpointid);
                            jsonObjectuploadImageModels.addProperty("FileName", jsonObject.getString("FileName"));
                            jsonObjectuploadImageModels.addProperty("FilePath", jsonObject.getString("FilePath"));
                            uploadImageModels.add(jsonObjectuploadImageModels);
                            Log.i("Iagemodel", uploadImageModels.toString());
                        }
                    } catch (Exception e) {

                    }

                }
                if (slectedType == 1)
                    txt_img_nctype.setText("File Uploaded Successfully..");
                if (slectedType == 3)
                    txt_img.setText("File Uploaded Successfully..");
                if (slectedType == 2)
                    txt_video_nctype.setText("File Uploaded Successfully..");
                if (txt_uploadSuccess1 != null) {
                    if (uploadstatus == 11) {
                        txt_uploadSuccess1.setVisibility(View.VISIBLE);
                        txt_uploadSuccess1.setText("File Uploaded Successfully.");

                    }
                    if (uploadstatus == 22) {
                        txt_uploadSuccess2.setVisibility(View.VISIBLE);
                        txt_uploadSuccess2.setText("File Uploaded Successfully.");
                    }
                    if (uploadstatus == 33) {
                        txt_uploadSuccess3.setVisibility(View.VISIBLE);
                        txt_uploadSuccess3.setText("File Uploaded Successfully.");
                    }
                    if (uploadstatus == 44) {
                        txt_uploadSuccess4.setVisibility(View.VISIBLE);
                        txt_uploadSuccess4.setText("File Uploaded Successfully.");
                    }
                    if (uploadstatus == 55) {
                        txt_uploadSuccess5.setVisibility(View.VISIBLE);
                        txt_uploadSuccess5.setText("File Uploaded Successfully.");
                    }
                    uploadstatus=0;
                }
            } catch (Exception e) {

            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(context);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setMessage("File Uploading ....");
            progressDialog.show();
        }


        @Override
        protected List<String> doInBackground(Object[] objects) {


            try {

                try {
                    checkpointid = Integer.parseInt(objects[1].toString().trim());
                } catch (NumberFormatException e) {
                    checkpointid = 0;
                }


                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                String charset = "UTF-8";
                File uploadFile1 = new File(objects[0].toString().trim());
                String requestURL = Constants.BASE_URL + "processInspection/uploadFile";
                MultipartUtility multipart = new MultipartUtility(requestURL, charset);
//            multipart.addHeaderField("User-Agent", "CodeJava");
//            multipart.addHeaderField("Test-Header", "Header-Value");
                // multipart.addFormField("friend_id", "Cool Pictures");
                String UplaodType = "";
                if (slectedType == 2)
                    UplaodType = "Video";
                else
                    UplaodType = "Image";

                multipart.addFormField("FileType", UplaodType);
                multipart.addFormField("InspectionCheckPointId", "" + checkpointid);
                multipart.addFilePart("UploadFile", uploadFile1);
/*

                for(FileModel fileModel:files) {
                    multipart.addFormField("FileType", fileModel.getType());
                    multipart.addFilePart("UploadFile", fileModel.getFile());
                }

 */


                List<String> response = multipart.finish();
                return response;
            } catch (Exception e) {

            }

            return null;
        }
    }


    private void uploadFileVideo(String path) {

        try {
            new UploadFile().execute(path,"0");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void uploadimage(View view) {
        try {
            uploadFile(masterimagepath, "Image");
        } catch (Exception e) {

        }
    }

    public void uploadvideo(View view) {
        try {
            uploadFile(videofilename, "Video");
        } catch (Exception e) {

        }
    }

    public void uploadncimage(View view) {
        try {
            uploadFile(imagefilename, "Image");
        } catch (Exception e) {

        }
    }


}