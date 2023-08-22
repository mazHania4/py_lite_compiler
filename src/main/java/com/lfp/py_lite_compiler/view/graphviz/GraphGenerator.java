package com.lfp.py_lite_compiler.view.graphviz;

import guru.nidi.graphviz.attribute.*;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.*;

import static guru.nidi.graphviz.model.Factory.mutGraph;

import java.io.*;

public class GraphGenerator {

    public void lexemeGraph(String lexeme) {
        MutableGraph g = mutGraph("example1").setDirected(true).graphAttrs().add(Rank.dir(Rank.RankDir.LEFT_TO_RIGHT));
        MutableNode previous = null;
        char[] chars = lexeme.toCharArray();
        if (chars.length == 1) {
            g.add(Factory.mutNode(String.valueOf(chars[0])).add(Shape.DOUBLE_CIRCLE));
        } else {
            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                Shape shape = (i != chars.length - 1) ? Shape.CIRCLE : Shape.DOUBLE_CIRCLE;
                MutableNode currentNode = Factory.mutNode(String.valueOf(c)).add(shape);
                if (previous != null) {
                    g.add(previous.addLink(currentNode));
                }
                previous = currentNode;
            }
        }
        try {
            Graphviz.fromGraph(g).width(500).render(Format.PNG).toFile(new File("src/main/resources/generated/graph.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
