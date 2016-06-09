package su.dreamteam.lonja.trainingdiaryfinal;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.databinding.InverseBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.common.math.DoubleMath;

import java.util.Date;
import java.util.Objects;

public class Bindings {

    @BindingConversion
    public static CharSequence convertDoubleToString(Double value) {
        if (value == null) {
            return "";
        } else if (DoubleMath.isMathematicalInteger(value)) {
            return Integer.toString(value.intValue());
        } else {
            return value.toString();
        }
    }

    @BindingConversion
    public static String convertDateToStringDateTime(Date value) {
        if (value == null) {
            return "";
        }
        return DateFormat.format("dd MMMM yyyy, HH:mm", value).toString();
    }

    @BindingConversion
    public static String convertDateToStringDate(Date value) {
        if (value == null) {
            return "";
        }
        return DateFormat.format("dd MMMM yyyy", value).toString();
    }

    @BindingAdapter("android:visibility")
    public static void setVisibility(View view, boolean visibility) {
        if (visibility) {
            view.setVisibility(View.VISIBLE);
            return;
        }
        view.setVisibility(View.GONE);
    }

    @InverseBindingAdapter(attribute = "android:text", event = "android:textAttrChanged")
    public static Double getDoubleFromTextView(TextView textView) {
        String value = textView.getText().toString();
        return Objects.equals(value, "") ? null : Double.parseDouble(value);
    }


    @BindingAdapter("android:checkedButton")
    public static void setCheckedByGender(RadioGroup radioGroup, @NonNull String gender) {
        if (Objects.equals(gender, "male")) {
            radioGroup.check(R.id.radio_male);
            return;
        }
        radioGroup.check(R.id.radio_female);
    }

    @BindingAdapter("android:src")
    public static void setImageByGender(ImageView imageView, @NonNull String gender) {
        if (Objects.equals(gender, "male")) {
            imageView.setImageResource(R.drawable.man_fit);
            return;
        } else if (Objects.equals(gender, "female")) {
            imageView.setImageResource(R.drawable.women_fit);
            return;
        }
        imageView.setImageResource(R.drawable.button_background);
    }

    @InverseBindingAdapter(attribute = "android:checkedButton", event = "android:checkedButtonAttrChanged")
    public static String getGender(RadioGroup radioGroup) {
        if (radioGroup.getCheckedRadioButtonId() == R.id.radio_male) {
            return "male";
        }
        return "female";
    }

    @BindingAdapter("android:onTextChanged")
    public static void setTextChangeListener(TextView textView, TextViewBindingAdapter.AfterTextChanged listener) {
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                listener.afterTextChanged(s);
            }
        };
        textView.addTextChangedListener(watcher);
    }
}