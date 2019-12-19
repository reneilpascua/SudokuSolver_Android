package projects.reneilpascua.sudokusolver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import projects.reneilpascua.sudokusolver.board.Cell;

public class CellAdapter2 extends RecyclerView.Adapter<CellAdapter2.ViewHolder> {

    private Cell[] mData;
    private LayoutInflater mInflater;
    private boolean bind;

    // data is passed into the constructor
    CellAdapter2(Context context, Cell[] data, boolean bind) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.bind = bind;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.square_layout, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (bind) holder.et.setText(Integer.toString(mData[position].num));
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.length;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        EditText et;

        ViewHolder(View itemView) {
            super(itemView);
            et = itemView.findViewById(R.id.et_value);
        }

    }

    // convenience method for getting data at click position
    Cell getItem(int id) {
        return mData[id];
    }

}
