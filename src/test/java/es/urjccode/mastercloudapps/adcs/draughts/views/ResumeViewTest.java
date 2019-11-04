package es.urjccode.mastercloudapps.adcs.draughts.views;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import es.urjccode.mastercloudapps.adcs.draughts.controllers.ResumeController;
import es.urjccode.mastercloudapps.adcs.draughts.models.Game;
import es.urjccode.mastercloudapps.adcs.draughts.models.State;
import es.urjccode.mastercloudapps.adcs.draughts.utils.YesNoDialog;
import es.urjccode.mastercloudapps.adcs.draughts.views.console.ResumeView;

@RunWith(MockitoJUnitRunner.class)
public class ResumeViewTest {

	@Mock
	ResumeController resumeController;

	@Mock
	YesNoDialog yesNoDialog;

    @InjectMocks
    ResumeView resumeView;
    
	@Before
	public void initMocks() {
        resumeController = new ResumeController(new Game(), new State());
		MockitoAnnotations.initMocks(this);
	}
    
    @Test
    public void testGivenResumeViewWhenContinueThenClear(){
        when(yesNoDialog.read("¿Queréis jugar otra?")).thenReturn(true);
		resumeView.interact(resumeController);
		verify(resumeController).resume(true);
    }

}