package jmgl_common;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import config.WindowPropsMananger;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import common.ImageViewer;

public class ImageViewDlg extends Dialog {

	protected Shell shell;
	
	/**
	 * Create the dialog.
 * @param parent
	 * @param style
	 */
	public ImageViewDlg(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
		createContents();
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public int open() {
		
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return WindowPropsMananger.CLOSE_FLAG;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(1200, 500);
		shell.setText("\u67E5\u770B\u7B7E\u540D");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		shell.setLocation((Display.getDefault().getBounds().width - shell.getBounds().width)/2, (Display.getDefault().getBounds().height - shell.getBounds().height)/2);

		

	}
	
	public void shwoImg(byte[] wbytes,byte[] dbytes){
		ImageViewer watcherImg = new ImageViewer(shell, getStyle());
		ImageViewer doorImg = new ImageViewer(shell, getStyle());
				
		watcherImg.shwoImageByBytes("Ѻ�⾯��ǩ����", wbytes, 600, 500);
		doorImg.shwoImageByBytes("���ž���ǩ����", dbytes, 600, 500);
		shell.layout();
		
		
	}
	
	public void shwoImg(byte[] wbytes,byte[] dbytes,byte[] odbytes){
		ImageViewer watcherImg = new ImageViewer(shell, getStyle());
		ImageViewer doorImg = new ImageViewer(shell, getStyle());
		ImageViewer out_doorImg = new ImageViewer(shell, getStyle());
				
		watcherImg.shwoImageByBytes("���쾯��ǩ����", wbytes, 600, 500);
		doorImg.shwoImageByBytes("���ž���ǩ����������", dbytes, 600, 500);
		out_doorImg.shwoImageByBytes("���ž���ǩ����������", odbytes, 600, 500);
		shell.layout();
		
		
	}
}
