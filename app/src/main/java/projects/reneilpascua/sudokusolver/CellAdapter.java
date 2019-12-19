package projects.reneilpascua.sudokusolver;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import projects.reneilpascua.sudokusolver.board.Cell;

public class CellAdapter extends BaseAdapter {

    private Context context;
    private Cell[] cells;
    private boolean enteringInitialVals;

    public CellAdapter(Context c, Cell[] s, boolean enteringInitialVals) {
        context = c;
        cells = s;
        this.enteringInitialVals = enteringInitialVals;
    }

    @Override
    public int getCount() {
        return cells.length;
    }

    @Override
    public Object getItem(int i) {
        return cells[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        Cell c = cells[i];
        LayoutInflater inflater = LayoutInflater.from(context);

        ViewHolder holder = null;

        if(convertView==null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.square_layout, null);
            holder.et = (EditText) convertView.findViewById(R.id.et_value);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

//        View v;
//        if (convertView==null) {
//            v = new View(context);
//            v = inflater.inflate(R.layout.square_layout,null);
//        } else {
//            v = (View) convertView;
//        }
//        return v;



        if (!enteringInitialVals) { // set text only if not entering initial vals
            holder.et.setText(Integer.toString(c.num));
        }
        return convertView;
    }

    public class ViewHolder {
        EditText et;
    }

}