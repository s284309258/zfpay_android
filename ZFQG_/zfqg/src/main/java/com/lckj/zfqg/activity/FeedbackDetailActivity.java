package com.lckj.zfqg.activity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.custom.WrapPictureExternalPreviewActivity;
import com.lckj.framework.data.DataManager;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.zfqg_network.GetUserFeedBackListBean;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.lckjlib.imageloader.ImageLoader;
import com.luck.picture.lib2.config.PictureConfig;
import com.luck.picture.lib2.entity.LocalMedia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedbackDetailActivity extends BaseActvity {
    @Inject
    DataManager dataManager;
    @Inject
    MyService myService;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_question)
    TextView tvQuestion;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.iv_icon2)
    ImageView ivIcon2;
    @BindView(R.id.tv_answer)
    TextView tvAnswer;
    @BindView(R.id.tv_time_answer)
    TextView tvTimeAnswer;
    @BindView(R.id.rl_answer)
    RelativeLayout rlAnswer;
    @BindView(R.id.left_action)
    TextView leftAction;
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.right_action)
    TextView rightAction;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    private GetUserFeedBackListBean.DataBean.UserFeedBackListBean data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntentParams();
        setContentView(R.layout.activity_feedback_detail);
        ButterKnife.bind(this);
        MainApplication.getInjectGraph().inject(this);
        initView();
    }

    private void getIntentParams() {
        Intent intent = getIntent();
        Serializable data = intent.getSerializableExtra("data");
        if (data instanceof GetUserFeedBackListBean.DataBean.UserFeedBackListBean) {
            this.data = (GetUserFeedBackListBean.DataBean.UserFeedBackListBean) data;
        }
    }

    @Override
    protected void initView() {
        customTitle.setText(R.string.问答详情);
        tvQuestion.setText(data.getFeedback_title());
        tvContent.setText(data.getFeedback_content());
        if (!TextUtils.isEmpty(data.getFeedback_img())) {
            recyclerView.setVisibility(View.VISIBLE);
            String[] split = data.getFeedback_img().split(",");
            final List<String> datas = Arrays.asList(split);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
            recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
                @Override
                public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                    super.getItemOffsets(outRect, view, parent, state);
                    outRect.left = outRect.top = dip2px(10);
                }
            });
            RecyclerView.Adapter adapter = new RecyclerView.Adapter() {
                @NonNull
                @Override
                public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                    View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_images, viewGroup, false);
                    return new ViewHolderFeedBack(inflate);
                }

                @Override
                public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
                    if (viewHolder instanceof ViewHolderFeedBack) {
                        ((ViewHolderFeedBack) viewHolder).setPostion(i);
                        ImageLoader.loadImage(datas.get(i), ((ViewHolderFeedBack) viewHolder).ivImg);
                        ((ViewHolderFeedBack) viewHolder).ivImg.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(v.getContext(), WrapPictureExternalPreviewActivity.class);
                                ArrayList<LocalMedia> localMedia  = new ArrayList<>();
                                for (int i1 = 0; i1 < datas.size(); i1++) {
                                    String s = datas.get(i1);
                                    localMedia.add(new LocalMedia().setPath(s));
                                }
                                intent.putExtra(PictureConfig.EXTRA_PREVIEW_SELECT_LIST, (Serializable) localMedia);
                                intent.putExtra(PictureConfig.EXTRA_POSITION, ((ViewHolderFeedBack) viewHolder).getPostion());
                                MainApplication.getInstance().getLastActivity().startActivityForResult(intent, PictureConfig.CHOOSE_REQUEST);
                                MainApplication.getInstance().getLastActivity().overridePendingTransition(com.luck.picture.lib2.R.anim.a5, 0);
                            }
                        });
                    }
                }

                @Override
                public int getItemCount() {
                    return datas.size();
                }



            };
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        tvTime.setText(data.getCre_time());
        if (TextUtils.isEmpty(data.getFeedback_answer())) {
            rlAnswer.setVisibility(View.GONE);
        } else {
            rlAnswer.setVisibility(View.VISIBLE);
            tvAnswer.setText(data.getFeedback_answer());
            tvTimeAnswer.setText(data.getUp_time());
        }
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.left_action)
    public void onViewClicked() {
        finish();
    }

    class ViewHolderFeedBack extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_img)
        ImageView ivImg;
        private int postion;

        public ViewHolderFeedBack(View inflate) {
            super(inflate);
            ButterKnife.bind(this,inflate);
        }

        public void setPostion(int postion) {
            this.postion = postion;
        }

        public int getPostion() {
            return postion;
        }
    }

}
