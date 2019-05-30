package pt.up.fe.pangolin.core.instrumentation;

import javassist.CtBehavior;
import javassist.CtClass;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.MethodInfo;

public class StackSizePass implements Pass {

	@Override
	public final Outcome transform (CtClass c) throws Exception {
		for (CtBehavior b : c.getDeclaredBehaviors()) {
			MethodInfo info = b.getMethodInfo();
			CodeAttribute ca = info.getCodeAttribute();

			if (ca != null) {
				int ss = ca.computeMaxStack();
				ca.setMaxStack(ss);
			}
		}

		return Outcome.CONTINUE;
	}
}