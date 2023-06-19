package vn.name.appbanhang.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;

import io.paperdb.Paper;
import vn.name.appbanhang.R;
import vn.name.appbanhang.adapter.GioHangAdapter;
import vn.name.appbanhang.model.EventBus.TinhTongEvent;
import vn.name.appbanhang.model.GioHang;
import vn.name.appbanhang.utils.Utils;

public class GioHangActivity extends AppCompatActivity {
    TextView giohangtrong, tongtien;
    Toolbar toolbar;
    RecyclerView recyclerView;
    Button btnmuahang;
    GioHangAdapter adapter;
    long tongtiensp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        initView();
        initControl();

        if(Utils.mangmuahang!=null){
            Utils.mangmuahang.clear();
        }
        tinhTongTien();
    }

    private void tinhTongTien() {
        tongtiensp = 0;
        for(int i = 0; i < Utils.mangmuahang.size(); i++){
            tongtiensp = tongtiensp + (Utils.manggiohang.get(i).getGiasp()* Utils.manggiohang.get(i).getSoluong());
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tongtien.setText(decimalFormat.format(tongtiensp));

    }

    private void initControl() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        if(Utils.manggiohang.size()==0){
            giohangtrong.setVisibility(View.VISIBLE);
        }else{
            adapter =new GioHangAdapter(getApplicationContext(),Utils.manggiohang);
            recyclerView.setAdapter(adapter);
        }
        btnmuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tongtiensp == 0){
                    Toast.makeText(getApplicationContext(), "Chưa có sản phẩm nào được chọn, tiếp tục mua sắm", Toast.LENGTH_SHORT).show();
                } else {
                    if(Paper.book().read("islogin")==null){
                        Toast.makeText(getApplicationContext(), "Vui lòng đăng nhập", Toast.LENGTH_SHORT).show();
                        Intent backLogin = new Intent(getApplicationContext(),DangNhapActivity.class);
                        startActivity(backLogin);
                    }else {
                        Intent intent = new Intent(getApplicationContext(), ThanhToanActivity.class);
                        intent.putExtra("tongtien", tongtiensp);
                        Utils.manggiohang.clear();
                        startActivity(intent);
                    }
                }
            }
        });
    }

    private void initView() {
        giohangtrong=findViewById(R.id.txtgiohangtrong);
        tongtien=findViewById(R.id.txttongtien);
        toolbar=findViewById(R.id.toobar);
        recyclerView=findViewById(R.id.recycleviewgiohang);
        btnmuahang=findViewById(R.id.btnmuahang);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();

    }
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void eventTinhTien(TinhTongEvent event){
        if(event != null){
            tinhTongTien();
        }
    }
}