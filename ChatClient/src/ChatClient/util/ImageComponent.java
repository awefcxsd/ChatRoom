package ChatClient.util;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

public class ImageComponent extends JComponent
	{
		private Image image;

		private Dimension size;

		public void setImage(Image image)
		{
			SwingUtilities.invokeLater(new ImageRunnable(image));
			
		}
		
		public void setImageSize(Dimension newSize)
		{
			if (!newSize.equals(size))
			{	//System.out.println("New size " + newSize + " from " + size);
				size = newSize;
				setSize(size);
			}
		}
		
		private class ImageRunnable implements Runnable
		{	private final Image newImage;

			public ImageRunnable(Image newImage)
			{
				super();
				this.newImage = newImage;
			}
	
			public void run()
			{	setImageInSwingThread(newImage);
			}
			
		}
		
		private synchronized void setImageInSwingThread(Image image)
		{
			this.image = image;
			final Dimension newSize = new Dimension(image.getWidth(null), image.getHeight(null));
			setImageSize(newSize);
			repaint();
		}

		public ImageComponent()
		{
			size = new Dimension(0, 0);
			setSize(size);
		}

		public synchronized void paint(Graphics g)
		{
			if (image != null)
				g.drawImage(image, 0, 0, this);
		}

		public synchronized Dimension getPreferredSize()
		{
			return size;
		}
	}