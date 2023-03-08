package LAB_1;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SubjectEvaluationTest {
    private SubjectEvaluation subjectEvaluation;

    @Before
    public void setUp(){
        subjectEvaluation = new SubjectEvaluation();
    }

    @After
    public void tearDown(){
        subjectEvaluation = null;
    }
    @Test
    public void lessThan50PercentageGives2(){
        Assert.assertEquals(2.0, SubjectEvaluation.evaluate(0.49, 0.5), 0.1);
    }

    @Test
    public void lessThan60PercentageGives3(){
        Assert.assertEquals(3.0, SubjectEvaluation.evaluate(0.55, 0.51, 0.54),0.1);
    }

    @Test
    public void lessThan70PercentagesGives3AndHalf(){
        Assert.assertEquals(3.5, SubjectEvaluation.evaluate(0.6, 0.7, 0.65),0.1);
    }

    @Test
    public void noMarksGives0(){
        Assert.assertEquals(0,SubjectEvaluation.evaluate(),0.1);
    }

    @Test
    public void negativePercentage(){
        Assert.assertEquals(0,SubjectEvaluation.evaluate(-0.5, 0.7, 0.65),0.1);
    }
    @Test
    public void CorrectEmail(){
        Assert.assertTrue(SubjectEvaluation.isValidEmail("someone@example.com"));
    }
    @Test
    public void FalseEmail(){
        Assert.assertFalse(SubjectEvaluation.isValidEmail("someone@example"));
    }
    @Test
    public void ValidUrl(){
        Assert.assertSame("url is valid",SubjectEvaluation.isValidUrl("ftp://foo.bar.com/"));
    }
    @Test
    public void UrlNotHttps(){
        Assert.assertNotSame("url is valid",SubjectEvaluation.isUrlHttps("ftp://foo.bar.com/"));
    }
    @Test
    public void NotNull(){
        Assert.assertNotNull("5",SubjectEvaluation.isDouble("5"));
    }
}