package com.why.project.assetsutilsdemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.why.project.assetsutilsdemo.utils.AssetsUtils;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = "MainActivity";

	private Button btn_getFileNamesArray;
	private Button loadAssetsHtml;
	private Button getStringFromAssert;
	private Button copyFolderFromAssetsToSD;
	private Button copyOneFileFromAssetsToSD;
	private Button getImageBitmapFromAssetsFile;
	private Button getStringFromRaw;
	private Button writeFileToData;
	private Button getStringFileFromData;

	private TextView id_text;
	private ImageView id_img;
	private WebView id_web;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initViews();
		initEvents();
	}

	private void initViews() {
		btn_getFileNamesArray = (Button) findViewById(R.id.btn_getFileNamesArray);
		loadAssetsHtml = (Button) findViewById(R.id.loadAssetsHtml);
		getStringFromAssert = (Button) findViewById(R.id.getStringFromAssert);
		copyFolderFromAssetsToSD = (Button) findViewById(R.id.copyFolderFromAssetsToSD);
		copyOneFileFromAssetsToSD = (Button) findViewById(R.id.copyOneFileFromAssetsToSD);
		getImageBitmapFromAssetsFile = (Button) findViewById(R.id.getImageBitmapFromAssetsFile);
		getStringFromRaw = (Button) findViewById(R.id.getStringFromRaw);
		writeFileToData = (Button) findViewById(R.id.writeFileToData);
		getStringFileFromData = (Button) findViewById(R.id.getStringFileFromData);

		id_text = (TextView) findViewById(R.id.id_text);
		id_img = (ImageView) findViewById(R.id.id_img);
		id_web = (WebView) findViewById(R.id.id_web);

	}

	private void hiddenAll(){
		id_text.setVisibility(View.GONE);
		id_img.setVisibility(View.GONE);
		id_web.setVisibility(View.GONE);
	}

	private void initEvents() {
		btn_getFileNamesArray.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//获取assets的指定目录中的所有文件及子目录名数组
				String[] assetsWwwFilesNameArray = AssetsUtils.getFileNamesArray(MainActivity.this,"why");
				String filesName = "--why\n";
				for(int i=0;i<assetsWwwFilesNameArray.length;i++){
					filesName += "  --" + assetsWwwFilesNameArray[i] + "\n";
				}
				//显示
				hiddenAll();
				id_text.setVisibility(View.VISIBLE);
				id_text.setText(filesName);
			}
		});

		loadAssetsHtml.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//使用webview加载assets目录下的html网页
				AssetsUtils.loadAssetsHtml(id_web,"why/test.html");
				//显示
				hiddenAll();
				id_web.setVisibility(View.VISIBLE);
			}
		});

		getStringFromAssert.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//访问assets目录下的资源文件，获取文件中的字符串
				String listdata = AssetsUtils.getStringFromAssert(MainActivity.this,"listitemdata.txt");
				//显示
				hiddenAll();
				id_text.setVisibility(View.VISIBLE);
				id_text.setText(listdata);
			}
		});

		copyFolderFromAssetsToSD.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//从assets目录中复制整个文件夹内容到SD卡中
				String sdFolderPath = Environment.getExternalStorageDirectory() + "/why";
				AssetsUtils.copyFolderFromAssetsToSD(MainActivity.this, "why", sdFolderPath);
			}
		});

		copyOneFileFromAssetsToSD.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//从assets目录中复制单个文件到SD卡中
				String sdFilePath = Environment.getExternalStorageDirectory() + "/why/listitemdata.txt";
				AssetsUtils.copyOneFileFromAssetsToSD(MainActivity.this,"listitemdata.txt",sdFilePath);
			}
		});

		getImageBitmapFromAssetsFile.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//获取assets目录下的图片资源的bitmap对象
				Bitmap bitmap = AssetsUtils.getImageBitmapFromAssetsFile(MainActivity.this, "why/img/image.png");
				//显示
				hiddenAll();
				id_img.setVisibility(View.VISIBLE);
				id_img.setImageBitmap(bitmap);
			}
		});

		getStringFromRaw.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//从res中的raw文件夹中获取文件并读取数据（资源文件只能读不能写）
				String rawFileStr = AssetsUtils.getStringFromRaw(MainActivity.this, R.raw.rawtext);
				//显示
				hiddenAll();
				id_text.setVisibility(View.VISIBLE);
				id_text.setText(rawFileStr);
			}
		});

		writeFileToData.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//写入（./data/data/包名/file）文件里面内容
				AssetsUtils.writeFileToData(MainActivity.this, "datatext.txt", "这是写入./data/data/包名/下的文件的内容");
			}
		});

		getStringFileFromData.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//读取./data/data/包名/file/下面的文件内容
				String dataFileStr = AssetsUtils.getStringFileFromData(MainActivity.this, "datatext.txt");
				//显示
				hiddenAll();
				id_text.setVisibility(View.VISIBLE);
				id_text.setText(dataFileStr);
			}
		});


	}
}
