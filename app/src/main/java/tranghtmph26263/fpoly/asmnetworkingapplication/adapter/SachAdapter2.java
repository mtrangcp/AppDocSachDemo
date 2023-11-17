package tranghtmph26263.fpoly.asmnetworkingapplication.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import tranghtmph26263.fpoly.asmnetworkingapplication.R;
import tranghtmph26263.fpoly.asmnetworkingapplication.entity.Sach;

public class SachAdapter2 extends BaseAdapter {
    List<Sach> listSach;

    public SachAdapter2(List<Sach> listSach) {
        this.listSach = listSach;
    }

    @Override
    public int getCount() {
        return listSach.size();
    }

    @Override
    public Object getItem(int i) {
        Sach obj = listSach.get(i);
        return obj;
    }

    @Override
    public long getItemId(int i) {
        Sach obj = listSach.get(i);
        return obj.get__v();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView;
        if ( view == null){
            itemView = View.inflate(viewGroup.getContext(), R.layout.item_sach_chitiet_tacgia, null);
        }else {
            itemView = view;
        }

        Sach objSach = listSach.get(i);

        ImageView imgAnhBia = itemView.findViewById(R.id.img_anhBia1);
        TextView tv_tenSach = itemView.findViewById(R.id.tv_tenSach1);
        TextView tv_namSX = itemView.findViewById(R.id.tv_namSX1);
        TextView tv_slBluan = itemView.findViewById(R.id.tv_slBluan1);
        TextView tv_mota = itemView.findViewById(R.id.tv_mota1);

        tv_tenSach.setText(objSach.getTenSach());
        tv_namSX.setText("Năm sx: "+objSach.getNamsx());
        tv_mota.setText("Mô tả: "+objSach.getMoTa());
        tv_slBluan.setText("Số lượng bình luận: "+objSach.getBinhLuan().size());
        Glide.with(viewGroup.getContext()).load(objSach.getAnhBia()).into(imgAnhBia);

        return itemView;
    }
}
