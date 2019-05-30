package pt.up.fe.pangolin.core.instrumentation.granularity;

import javassist.CtClass;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.MethodInfo;

public class LineGranularity extends AbstractGranularity {

	private int currentLine = -1;

	public LineGranularity(CtClass c, MethodInfo mi, CodeIterator ci) {
		super(c, mi, ci);
	}

	@Override
	public boolean instrumentAtIndex(int index, int instrumentationSize) {
		int previousLine = currentLine;
		currentLine = mi.getLineNumber(index);
		return currentLine != previousLine;
	}

	@Override
	public boolean stopInstrumenting() {
		return false;
	}	
}