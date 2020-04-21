package com.dev.bruno.learning.sorting.problems;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MergeX;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {

    private final LineSegment[] segments;
    private int segmentsSize = 0;

    public BruteCollinearPoints(final Point[] points) {
        final Point[] p = new Point[points.length];
        System.arraycopy(points, 0, p, 0, points.length);

        checkIfIsValidArray(p);

        segments = analyzeSegments(p);
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

    private LineSegment[] analyzeSegments(final Point[] points) {
        final LineSegment[] tmpSegments = new LineSegment[points.length * 4];
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    for (int m = k + 1; m < points.length; m++) {
                        final Point p = points[i];
                        final Point q = points[j];
                        final Point r = points[k];
                        final Point s = points[m];
                        if (Double.compare(p.slopeTo(q), p.slopeTo(r)) == 0
                                && Double.compare(p.slopeTo(q), p.slopeTo(s)) == 0) {
                            tmpSegments[segmentsSize++] = new LineSegment(p, s);
                        }
                    }
                }
            }
        }
        final LineSegment[] resultSegments = new LineSegment[segmentsSize];
        for (int i = 0; i < segmentsSize; i++) {
            resultSegments[i] = tmpSegments[i];
        }
        return resultSegments;
    }

    public int numberOfSegments() {
        return segmentsSize;
    }

    public LineSegment[] segments() {
        final LineSegment[] returnSegments = new LineSegment[segments.length];
        System.arraycopy(segments, 0, returnSegments, 0, segments.length);
        return returnSegments;
    }

    public static void main(final String[] args) {
        final In in = new In("/home/casa/dev/git/learning/inputs/collinear/input1.txt");
        final int N = in.readInt();
        final Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            final String s = in.readString();
            if ("null".equals(s)) {
                points[i] = null;
            } else {
                final int x = Integer.parseInt(s);
                final int y = in.readInt();
                points[i] = new Point(x, y);
            }
        }

        final BruteCollinearPoints collinear = new BruteCollinearPoints(points);

        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (final Point p : points) {
            p.draw();
        }
        StdDraw.show();

        for (final LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
