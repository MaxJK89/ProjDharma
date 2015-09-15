
import javax.imageio.ImageIO;

import java.awt.*;
import java.io.File;

import java.awt.image.BufferedImage;

import com.xuggle.mediatool.IMediaReader;
import com.xuggle.mediatool.MediaListenerAdapter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.mediatool.event.IVideoPictureEvent;
import com.xuggle.xuggler.Global;

/**
 * Created by angboty on 9/15/2015.
 */
public class App extends MediaListenerAdapter {
    public static int TAKE_START;

    public static FileHelper fileHelper;

    public static int FRAME_COUNT = 0;

    /**
     * The number of seconds between frames.
     */
    public static final double SECONDS_BETWEEN_FRAMES = 0.02;

    /**
     * The number of micro-seconds between frames.
     */
    public static final long MICRO_SECONDS_BETWEEN_FRAMES =
            (long)(Global.DEFAULT_PTS_PER_SECOND * SECONDS_BETWEEN_FRAMES);

    /** Time of last frame write. */

    private static long mLastPtsWrite = Global.NO_PTS;

    /**
     * The video stream index, used to ensure we display frames from one
     * and only one video stream from the media container.
     */

    private int mVideoStreamIndex = -1;



    /**
     * Takes a media container (file) as the first argument, opens it and
     *  writes some of it's video frames to PNG image files in the
     *  temporary directory.
     *
     * @param args must contain one string which represents a filename
     */

    public static void main(String[] args)
    {
        fileHelper = new FileHelper();
        ColorSec.setUpHashMap();

        new App("C:\\Users\\angboty\\Downloads\\COLORTEST2.MP4");

//        GetColor.getColorFromImage(new File("C:\\Users\\angboty\\AppData\\Local\\Temp\\frame5545981322612992840.jpg"), 50, 50);
    }




    /** Construct a DecodeAndCaptureFrames which reads and captures
     * frames from a video file.
     *
     * @param filename the name of the media file to read
     */

    public App(String filename)
    {
        // create a media reader for processing video

        IMediaReader reader = ToolFactory.makeReader(filename);

        // stipulate that we want BufferedImages created in BGR 24bit color space
        reader.setBufferedImageTypeToGenerate(BufferedImage.TYPE_3BYTE_BGR);

        reader.addListener(this);


        while (reader.readPacket() == null)
            do {} while(false);
    }

    /**
     * Called after a video frame has been decoded from a media stream.
     * Optionally a BufferedImage version of the frame may be passed
     * if the calling {@link IMediaReader} instance was configured to
     * create BufferedImages.
     *
     * This method blocks, so return quickly.
     */

    public void onVideoPicture(IVideoPictureEvent event)
    {
        try
        {
            // if the stream index does not match the selected stream index,
            // then have a closer look

            if (event.getStreamIndex() != mVideoStreamIndex)
            {
                // if the selected video stream id is not yet set, go ahead an
                // select this lucky video stream

                if (-1 == mVideoStreamIndex)
                    mVideoStreamIndex = event.getStreamIndex();

                    // otherwise return, no need to show frames from this video stream

                else
                    return;
            }

            // if uninitialized, backdate mLastPtsWrite so we get the very
            // first frame

            if (mLastPtsWrite == Global.NO_PTS)
                mLastPtsWrite = event.getTimeStamp() - MICRO_SECONDS_BETWEEN_FRAMES;

            // if it's time to write the next frame

            if (event.getTimeStamp() - mLastPtsWrite >= MICRO_SECONDS_BETWEEN_FRAMES)
            {
                // Make a temporary file name

//                File file = File.createTempFile("frame", ".jpg");
                File file = new File(fileHelper.FILE_DIRECTORY+String.valueOf(FRAME_COUNT)+".jpg");

                // write out PNG

                ImageIO.write(event.getImage(), "jpg", file);
                FRAME_COUNT++;

                // indicate file written

                double seconds = ((double)event.getTimeStamp())
                        / Global.DEFAULT_PTS_PER_SECOND;
//                System.out.printf("at elapsed time of %6.3f seconds wrote: %s\n",
//                        seconds, file);

//                if (GetColor.getColorFromImage(file, 50,50).equals(ColorSec.ORANGE)) {
////                    System.out.println("RED RED RED");
//                    TAKE_START++;
//
//                    if (TAKE_START == 3) {
//                        System.out.println("Start the take message");
//                    }
//
//                } else {
//                    System.out.println(GetColor.getColorFromImage(file, 50,50));
//                }

                decodeColor(file);

                // update last write time

                mLastPtsWrite += MICRO_SECONDS_BETWEEN_FRAMES;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    private static void decodeColor(File file) {
        String color = GetColor.getColorFromImage(file, 50,50);
        System.out.print(ColorSec.colorDecode.get(color)+"\n");
    }
}
