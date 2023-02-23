package com.example.rotationsensor;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public
class CrossFloating {
    // initialize our cube
    private final FloatBuffer mVertexBuffer;
    private final FloatBuffer mColorBuffer;
    private final ByteBuffer mIndexBuffer;

    public CrossFloating() {
        final float[] vertices = {
                0, 0, 1,
                1, 0, 1,
                -1, 0, 1,
                0, 1, 1,
                0, -1, 1,
        };
        final float[] colors = {
                0, 0, 0, 1,
                1, 0, 0, 1,
                1, 1, 0, 1,
                0, 1, 0, 1,
                0, 0, 1, 1,
        };
        final byte[] indices = {
                0, 1,
                0, 2,
                0, 3,
                0, 4,
        };

        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        mVertexBuffer = vbb.asFloatBuffer();
        mVertexBuffer.put(vertices);
        mVertexBuffer.position(0);

        ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length * 4);
        cbb.order(ByteOrder.nativeOrder());
        mColorBuffer = cbb.asFloatBuffer();
        mColorBuffer.put(colors);
        mColorBuffer.position(0);

        mIndexBuffer = ByteBuffer.allocateDirect(indices.length);
        mIndexBuffer.put(indices);
        mIndexBuffer.position(0);
    }

    public void draw(GL10 gl) {
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glFrontFace(GL10.GL_CW);
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, mColorBuffer);
        gl.glDrawElements(GL10.GL_LINES, 8, GL10.GL_UNSIGNED_BYTE, mIndexBuffer);
    }
}
