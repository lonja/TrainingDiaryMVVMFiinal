package su.dreamteam.lonja.trainingdiaryfinal;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.databinding.InverseBindingAdapter;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;

import com.google.common.math.DoubleMath;

import org.w3c.dom.Text;

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
}