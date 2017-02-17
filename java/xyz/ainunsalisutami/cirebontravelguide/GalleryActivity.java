package xyz.ainunsalisutami.cirebontravelguide;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yalantis.flipviewpager.adapter.BaseFlipAdapter;
import com.yalantis.flipviewpager.utils.FlipSettings;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import xyz.ainunsalisutami.cirebontravelguide.R;
import xyz.ainunsalisutami.cirebontravelguide.model.Gallery;
import xyz.ainunsalisutami.cirebontravelguide.util.Utils;

public class GalleryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        final ListView list = (ListView) findViewById(R.id.galeri);

        FlipSettings settings = new FlipSettings.Builder().defaultPage(1).build();
        list.setAdapter(new GalleryAdapter(this, Utils.galeri, settings));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Gallery f = (Gallery) list.getAdapter().getItem(position);

                Toast.makeText(GalleryActivity.this, f.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    class GalleryAdapter extends BaseFlipAdapter<Gallery> {
        private final int PAGES = 3;
        private int[] IDS_INTEREST = {R.id.interest_1, R.id.interest_2, R.id.interest_3};

        public GalleryAdapter(Context context, List<Gallery> items, FlipSettings settings){
            super(context, items, settings);
        }

        @Override
        public View getPage(int position, View convertView, ViewGroup parent, Gallery galeri1, Gallery galeri2){
            final GalleryHolder holder;

            if (convertView == null) {
                holder = new GalleryHolder();
                convertView = getLayoutInflater().inflate(R.layout.galeri_merge_page, parent, false);
                holder.leftIcon = (ImageView) convertView.findViewById(R.id.first);
                holder.rightIcon = (ImageView) convertView.findViewById(R.id.second);
                holder.infoPage = getLayoutInflater().inflate(R.layout.galeri_info, parent, false);
                holder.nama = (TextView) holder.infoPage.findViewById(R.id.nama);

                for (int id : IDS_INTEREST)
                    holder.interests.add((TextView) holder.infoPage.findViewById(id));

                convertView.setTag(holder);
            } else {
                holder = (GalleryHolder) convertView.getTag();
            }

            switch (position){
                case 1:
                    holder.leftIcon.setImageResource(galeri1.getIcon());
                    if (galeri2 != null)
                        holder.rightIcon.setImageResource(galeri2.getIcon());
                    break;
                default:
                    fillHolder(holder, position == 0 ? galeri1 : galeri2);
                    holder.infoPage.setTag(holder);
                    return holder.infoPage;
            }
            return convertView;
        }
        @Override
        public int getPagesCount(){return PAGES;}
        private void fillHolder(GalleryHolder holder, Gallery galeri){
            if (galeri == null)
                return;;
            Iterator<TextView> iViews = holder.interests.iterator();
            Iterator<String> iInterests = galeri.getInterests().iterator();
            while (iViews.hasNext() && iInterests.hasNext())
                iViews.next().setText(iInterests.next());
            holder.infoPage.setBackgroundColor(getResources().getColor(galeri.getBackground()));
            holder.nama.setText(galeri.getName());
        }
        class GalleryHolder {
            ImageView leftIcon;
            ImageView rightIcon;
            View infoPage;

            List<TextView> interests = new ArrayList<>();
            TextView nama;
        }
    }
}
