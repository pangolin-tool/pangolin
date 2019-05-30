package pt.up.fe.pangolin.core.instrumentation;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javassist.CtClass;
import pt.up.fe.pangolin.core.instrumentation.matchers.ActionTaker;

public class FilterPass implements Pass {

	private final List<ActionTaker> actionTakers = new LinkedList<ActionTaker> ();
    private Outcome fallbackOutcome = Outcome.CONTINUE;
    private Outcome acceptOutcome = Outcome.CONTINUE;
    private Outcome rejectOutcome = Outcome.CANCEL;


    public FilterPass (ActionTaker... actionTakers) {
        this.actionTakers.addAll(Arrays.asList(actionTakers));
    }

    public FilterPass (List<ActionTaker> actionTakers) {
        this.actionTakers.addAll(actionTakers);
    }
    
    public void add(ActionTaker actionTaker) {
    	this.actionTakers.add(actionTaker);
    }

    public FilterPass setFallbackOutcome (Outcome targetOutcome) {
        fallbackOutcome = targetOutcome;
        return this;
    }

    public FilterPass setAcceptOutcome (Outcome targetOutcome) {
        acceptOutcome = targetOutcome;
        return this;
    }

    public FilterPass setRejectOutcome (Outcome targetOutcome) {
        rejectOutcome = targetOutcome;
        return this;
    }

    @Override
    public Outcome transform (CtClass c) throws Exception {
        for (ActionTaker at : actionTakers) {
            ActionTaker.Action ret = at.getAction(c);

            switch (ret) {
            case ACCEPT:
                return acceptOutcome;

            case NEXT:
                continue;

            case REJECT:
                return rejectOutcome;

            default:
                continue;
            }
        }

        return fallbackOutcome;
    }
}
