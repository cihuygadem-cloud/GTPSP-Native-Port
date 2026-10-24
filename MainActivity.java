package id.rezzz.gtpspnativekit;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends Activity {
    private static final int PICK_GAME = 42;
    private TextView status;

    static {
        System.loadLibrary("gtpsp_native");
    }
    private native String nativeStatus();

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout page = new LinearLayout(this);
        page.setOrientation(LinearLayout.VERTICAL);
        page.setPadding(28, 36, 28, 28);
        page.setGravity(Gravity.CENTER_HORIZONTAL);
        page.setBackgroundColor(0xFF10151D);

        TextView title = new TextView(this);
        title.setText("GT PSP • Native Port Kit");
        title.setTextSize(25);
        title.setTextColor(0xFFFFFFFF);
        title.setGravity(Gravity.CENTER);
        page.addView(title, new LinearLayout.LayoutParams(-1, -2));

        TextView intro = new TextView(this);
        intro.setText("Android native-port workbench\\nThis is a development scaffold, not the playable game.");
        intro.setTextSize(15);
        intro.setTextColor(0xFFBFCBDA);
        intro.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams ip = new LinearLayout.LayoutParams(-1, -2);
        ip.topMargin = 18;
        page.addView(intro, ip);

        Button pick = new Button(this);
        pick.setText("Select my PSP game file");
        LinearLayout.LayoutParams bp = new LinearLayout.LayoutParams(-1, -2);
        bp.topMargin = 24;
        page.addView(pick, bp);
        pick.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            i.setType("*/*");
            i.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(i, PICK_GAME);
        });

        status = new TextView(this);
        status.setTextColor(0xFFE8EDF4);
        status.setTextSize(14);
        status.setText(nativeStatus());
        LinearLayout.LayoutParams sp = new LinearLayout.LayoutParams(-1, -2);
        sp.topMargin = 22;
        page.addView(status, sp);

        TextView note = new TextView(this);
        note.setText("\\nNext milestone: create a GT PSP-specific recompiler profile, implement PSP system calls, graphics, audio, input and saves, then test on device.");
        note.setTextColor(0xFF91A3B8);
        note.setTextSize(13);
        page.addView(note, new LinearLayout.LayoutParams(-1, -2));

        ScrollView scroll = new ScrollView(this);
        scroll.addView(page);
        setContentView(scroll);
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != PICK_GAME || resultCode != RESULT_OK || data == null) return;
        Uri uri = data.getData();
        String report = "Selected file: " + uri.getLastPathSegment() + "\\n";
        try (InputStream in = getContentResolver().openInputStream(uri)) {
            if (in == null) throw new Exception("Could not open file");
            byte[] buf = new byte[2048];
            int n = in.read(buf);
            String sample = n > 0 ? new String(buf, 0, n, StandardCharsets.ISO_8859_1) : "";
            boolean pbp = sample.startsWith("\\u0000PBP");
            report += "Header check: " + (pbp ? "PBP-like header detected" : "not a PBP header in the first bytes") + "\\n";
            report += "This quick check is not a full ISO parser and does not launch the game.";
        } catch (Exception e) {
            report += "Could not inspect file: " + e.getMessage();
        }
        status.setText(report + "\\n\\n" + nativeStatus());
    }
}
