package com.mridulahuja.groceryapp.tools;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.mridulahuja.groceryapp.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by mridul ahuja on 15/9/17.
 */

public class BitmapTools {

    private Context mContext;


    public BitmapTools(Context mContext) {
        this.mContext = mContext;
    }


    public String saveToInternalStorage(Bitmap bitmapImage, String imageName){
        ContextWrapper cw = new ContextWrapper(mContext);
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory, imageName);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 80, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(fos!=null) {
                    fos.close();
                }
            } catch (IOException | NullPointerException err) {
                err.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }


    public void saveToStorage(Bitmap bitmapImage, String fileName) {
        FileOutputStream fos = null;

        try {
            fos = null;
            fos = new FileOutputStream(Environment.getExternalStorageDirectory() + "/GroceryApp/imgCache/" + fileName);
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 80, fos);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(fos!=null) {
                    fos.close();
                }
            } catch (IOException | NullPointerException err) {
                err.printStackTrace();
            }
        }

    }


    public Bitmap loadImageFromStorage(String path, String imageName)
    {
        Bitmap b;

        try {
            File f=new File(path, imageName);
            b = BitmapFactory.decodeStream(new FileInputStream(f));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            b = null;
        }

        return b;
    }

}
