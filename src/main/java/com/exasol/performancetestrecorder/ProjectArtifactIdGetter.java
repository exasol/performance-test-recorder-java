package com.exasol.performancetestrecorder;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.xml.sax.SAXException;

import com.exasol.errorreporting.ExaError;

public class ProjectArtifactIdGetter {

    /**
     * Get the artifact-id of the project under test.
     * 
     * @return artifact ID
     */
    public String getProjectArtifactId() {
        try {
            final var documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            documentBuilderFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
            final var pom = documentBuilderFactory.newDocumentBuilder().parse(new File("pom.xml"));
            final var xPath = XPathFactory.newInstance().newXPath();
            return xPath.compile("/project/artifactId").evaluate(pom);
        } catch (final XPathExpressionException | SAXException | ParserConfigurationException | IOException exception) {
            throw new IllegalStateException(
                    ExaError.messageBuilder("E-PTRJ-1").message("Failed to get project name from pom file.").toString(),
                    exception);
        }
    }
}
