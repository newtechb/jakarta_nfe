/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.newtechb.nfe.services;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 *
 * @author bajinho
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/org/newtechb/nfe/services",
        glue = "org.newtechb"
)
public class RunCucumberTest {
}
