package common;

import java.io.ByteArrayInputStream;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

public class ImageViewer extends Composite {

	private Canvas canvas;
	private Label titleLable;
	private Image img;
	private int savewidth;
	private int saveheight;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ImageViewer(Composite parent, int style) {
		super(parent, SWT.BORDER);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		canvas = new Canvas(this, SWT.NONE);
		canvas.addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent arg0) {
				if(img!=null){
					GC gc = new GC(canvas);
					gc.drawImage(img, 0, 0, img.getBounds().width, img.getBounds().height, 0, 0, savewidth, saveheight);
				}
			}
		});
		
		titleLable = new Label(canvas, SWT.NONE);
		titleLable.setFont(SWTResourceManager.getFont("ËÎÌו", 14, SWT.BOLD));
		
		titleLable.setText("\u6807\u9898");
	}
	
	public void shwoImageByBytes(String title,byte[] bytes,int width,int height){
		titleLable.setText(title);
		savewidth = width;
		saveheight = height;
		titleLable.setBounds(0, 0, width, 20);
		
		if(bytes == null)return;
		ByteArrayInputStream a = new ByteArrayInputStream(bytes);
		img = new Image(null, a);
		canvas.redraw();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
