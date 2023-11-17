package tranghtmph26263.fpoly.asmnetworkingapplication.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import tranghtmph26263.fpoly.asmnetworkingapplication.R;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.TacGia;

public class TacgiaAdapter extends BaseAdapter {
    List<TacGia> listTacgia;

    public TacgiaAdapter(List<TacGia> listTacgia) {
        this.listTacgia = listTacgia;
    }

    @Override
    public int getCount() {
        return listTacgia.size();
    }

    @Override
    public Object getItem(int i) {
        TacGia tacGia = listTacgia.get(i);
        return tacGia;
    }

    @Override
    public long getItemId(int i) {
        TacGia tacGia = listTacgia.get(i);
        return tacGia.get__v();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView;
        if ( view == null){
            itemView = View.inflate(viewGroup.getContext(), R.layout.item_tacgia  , null);
        }else {
            itemView = view;
        }

        TacGia objTacgia = listTacgia.get(i);

        TextView tv_tenTG = itemView.findViewById(R.id.tv_tenTGia);
        TextView tv_namSinh = itemView.findViewById(R.id.tv_namSinh);
        TextView tv_slSach = itemView.findViewById(R.id.tv_slSach);

        tv_tenTG.setText(objTacgia.getTen());
        tv_namSinh.setText("Năm sinh: "+ objTacgia.getNamSinh());
        tv_slSach.setText("Số lượng sách: "+objTacgia.getSachs().size());


        return itemView;
    }
}
