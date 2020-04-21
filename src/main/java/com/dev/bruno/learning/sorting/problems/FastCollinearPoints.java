package com.dev.bruno.learning.sorting.problems;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MergeX;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {

    private int segmentsCounter;
    private final LineSegment[] segments;

    public FastCollinearPoints(final Point[] points) {
        final Point[] p = new Point[points.length];
        System.arraycopy(points, 0, p, 0, points.length);

        checkIfIsValidArray(p);

        this.segmentsCounter = 0;
        this.segments = this.analyze(p);
    }

    private void checkIfIsValidArray(final Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("points can't be null");
        }
        for (int i = 0; i < points.length; i++) {
            final Point p = points[i];
            if (p == null) {
                throw new IllegalArgumentException("point can't be null");
            }
        }
        MergeX.sort(points);
        for (int i = 0; i < points.length - 1; i++) {
            final Point p1 = points[i];
            final Point p2 = points[i + 1];
            if (p1.compareTo(p2) == 0) {
                throw new IllegalArgumentException("point can't be duplicated");
            }
        }
    }

    private LineSegment[] analyze(final Point[] points) {
        final LineSegment[] tmpSegments = new LineSegment[points.length * 4];

        for (int i = 0; i < points.length; i++) {
            final Point p = points[i];

            final int copySize = points.length - i - 1;
            final Point[] copyPoints = new Point[copySize];
            System.arraycopy(points, i + 1, copyPoints, 0, copySize);
            MergeX.sort(copyPoints, p.slopeOrder());

            int lineCounter = 2;
            for (int j = 1; j < copySize; j++) {
                if (Double.compare(p.slopeTo(copyPoints[j - 1]), p.slopeTo(copyPoints[j])) == 0) {
                    lineCounter++;
                } else {
                    if (lineCounter >= 4) {
                        tmpSegments[segmentsCounter++] = new LineSegment(p, copyPoints[j - 1]);
                    }
                    lineCounter = 2;
                }
            }
            if (lineCounter >= 4) {
                tmpSegments[segmentsCounter++] = new LineSegment(p, copyPoints[copyPoints.length - 1]);
            }
        }

        final LineSegment[] realSegments = new LineSegment[segmentsCounter];
        for (int i = 0; i < segmentsCounter; i++) {
            realSegments[i] = tmpSegments[i];
        }
        return realSegments;
    }

    public int numberOfSegments() {
        return segmentsCounter;
    }

    public LineSegment[] segments() {
        final LineSegment[] returnSegments = new LineSegment[segments.length];
        System.arraycopy(segments, 0, returnSegments, 0, segments.length);
        return returnSegments;
    }

    public static void main(final String[] args) {
        final In in = new In(args[0]);
        final int N = in.readInt();
        final Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            final int x = in.readInt();
            final int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (final Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        final FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (final LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
