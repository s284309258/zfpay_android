package com.lckj.zfqg.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lckj.framework.data.DataManager;
import com.lckj.jycm.R;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.zfqg.activity.HomePOSActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectDialog extends Dialog {

    @BindView(R.id.tv_pos)
    TextView tvPos;
    @BindView(R.id.tv_epos)
    TextView tvEpos;

    public SelectDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_select);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_pos, R.id.tv_epos})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_pos:
                getContext().startActivity(new Intent(getContext(), HomePOSActivity.class).putExtra("isPos", true));
                dismiss();
                break;
            case R.id.tv_epos:
                getContext().startActivity(new Intent(getContext(), HomePOSActivity.class).putExtra("isPos", false));
                dismiss();
                break;
        }
    }
}
