package dionizio.victor.organizze.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import dionizio.victor.organizze.R;
import dionizio.victor.organizze.databinding.FragmentFirstBinding;
import dionizio.victor.organizze.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

      binding = FragmentSecondBinding.inflate(inflater, container, false);
      return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}