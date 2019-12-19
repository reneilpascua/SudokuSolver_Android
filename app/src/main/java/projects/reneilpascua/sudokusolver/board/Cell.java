package projects.reneilpascua.sudokusolver.board;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.io.Serializable;

public class Cell implements Serializable {
    public int num = 0;
    public boolean isInit = false;


    @Override
    public String toString() {
        return Integer.toString(num);
    }

}
