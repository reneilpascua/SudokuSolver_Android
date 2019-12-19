package projects.reneilpascua.sudokusolver;

import android.content.Context;
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

    public CellAdapter(Context c, Cell[] s, boolean e) {
        context = c;
        cells = s;
        enteringInitialVals = e;
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

//    @Override
//    public View getView(int i, View convertView, ViewGroup parent) {
//        Cell c = cells[i];
//
//        if (convertView==null) {
//            LayoutInflater inf = LayoutInflater.from(context);
//            convertView = inf.inflate(R.layout.square_layout, null);
//        }
//
//        EditText et_value = (EditText) convertView.findViewById(R.id.et_value);
//        et_value.setText(Integer.toString(c.num));
//        et_value.setEnabled(true);
//        et_value.setFocusable(true);
//
//        return convertView;
//    }

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

        if (!enteringInitialVals) {
            holder.et.setText(
                    (c.num == 0) ? "" :
                            Integer.toString(c.num)
            );
        }
        return convertView;
    }

    public class ViewHolder {
        EditText et;
    }
}