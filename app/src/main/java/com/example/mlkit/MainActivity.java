package com.example.mlkit;


import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	private RecyclerView recyclerView;
	private AlbumsAdapter adapter;
	private List<Album> albumList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		initCollapsingToolbar();

		recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

		albumList = new ArrayList<>();
		adapter = new AlbumsAdapter(this, albumList);

		RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
		recyclerView.setLayoutManager(mLayoutManager);
		recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.setAdapter(adapter);

		prepareAlbums();

		try {
			Glide.with(this).load(R.drawable.cover).into((ImageView) findViewById(R.id.backdrop));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initializing collapsing toolbar
	 * Will show and hide the toolbar title on scroll
	 */
	private void initCollapsingToolbar() {
		final CollapsingToolbarLayout collapsingToolbar =
				(CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
		collapsingToolbar.setTitle(" ");
		AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
		appBarLayout.setExpanded(true);

		// hiding & showing the title when toolbar expanded & collapsed
		appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
			boolean isShow = false;
			int scrollRange = -1;

			@Override
			public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
				if (scrollRange == -1) {
					scrollRange = appBarLayout.getTotalScrollRange();
				}
				if (scrollRange + verticalOffset == 0) {
					collapsingToolbar.setTitle(getString(R.string.app_name));
					isShow = true;
				} else if (isShow) {
					collapsingToolbar.setTitle(" ");
					isShow = false;
				}
			}
		});
	}

	/**
	 * Adding few albums for testing
	 */
	private void prepareAlbums() {
		int[] covers = new int[]{
				R.drawable.textrecog,
				R.drawable.barcode,
				R.drawable.facedetec,
				R.drawable.imagelabel,
				R.drawable.landmark,
				R.drawable.custommodel,
				R.drawable.languageident,
				R.drawable.smartreply,
				R.drawable.translation,
				R.drawable.automl
				};

		Album a = new Album("Text Recognition",  covers[0]);
		albumList.add(a);

		a = new Album("Barcode Scanning", covers[1]);
		albumList.add(a);

		a = new Album("Face Detection", covers[2]);
		albumList.add(a);

		a = new Album("Image Labeling",  covers[3]);
		albumList.add(a);

		a = new Album("Landmark Detection", covers[4]);
		albumList.add(a);

		a = new Album("Object Recognition",covers[5]);
		albumList.add(a);

		a = new Album("Language Identification", covers[6]);
		albumList.add(a);

		a = new Album("Smart Reply",  covers[7]);
		albumList.add(a);

		a = new Album("Translate Text", covers[8]);
		albumList.add(a);

		a = new Album("Auto ML Vision", covers[9]);
		albumList.add(a);

		adapter.notifyDataSetChanged();
	}

	/**
	 * RecyclerView item decoration - give equal margin around grid item
	 */
	public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

		private int spanCount;
		private int spacing;
		private boolean includeEdge;

		public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
			this.spanCount = spanCount;
			this.spacing = spacing;
			this.includeEdge = includeEdge;
		}

		@Override
		public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
			int position = parent.getChildAdapterPosition(view); // item position
			int column = position % spanCount; // item column

			if (includeEdge) {
				outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
				outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

				if (position < spanCount) { // top edge
					outRect.top = spacing;
				}
				outRect.bottom = spacing; // item bottom
			} else {
				outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
				outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
				if (position >= spanCount) {
					outRect.top = spacing; // item top
				}
			}
		}
	}

	/**
	 * Converting dp to pixel
	 */
	private int dpToPx(int dp) {
		Resources r = getResources();
		return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
	}
}