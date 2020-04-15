package chat.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class NetWorkFragment extends Fragment {
    protected boolean isDestroyView = true;
    private View contentView;
    private LayoutInflater inflater;
    private ViewGroup container;

    public boolean isViewDestroy(){
        return isDestroyView;
    }
    @Override
    public final View onCreateView(LayoutInflater inflater,
                                   ViewGroup container, Bundle savedInstanceState) {
        isDestroyView = false;
        this.inflater = inflater;
        this.container = container;
        onCreateView(savedInstanceState);
        if (contentView == null)
            return super.onCreateView(inflater, container, savedInstanceState);

        return contentView;
    }

    protected void onCreateView(Bundle savedInstanceState) {

    }

    public View setContentView(int layoutResID) {
        return setContentView((ViewGroup) inflater.inflate(layoutResID, container,false));
    }

    public View setContentView(View view) {
        return contentView = view;
    }

    public View findViewById(int id) {
        if (contentView != null)
            return contentView.findViewById(id);
        return null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isDestroyView = true;
    }


}
