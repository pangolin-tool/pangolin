package pt.up.fe.pangolin.core.messaging;

import java.io.Serializable;

import pt.up.fe.pangolin.core.model.Node.Type;

public interface Message {

	public static class HandshakeMessage implements Message, Serializable {

		private static final long serialVersionUID = 4280869312305959655L;		
		public final String id;

		public HandshakeMessage (String id) {
			this.id = id;
		}

		protected HandshakeMessage () {
			this(null);
		}
	}

	public static class ByeMessage implements Message, Serializable {

		private static final long serialVersionUID = -4155285280206362746L;

	}

	public static class EndTransactionMessage implements Message, Serializable {

		private static final long serialVersionUID = 214052749607422773L;
		public final String transactionName;
		public final boolean[] activity;
		public final boolean isError;

		public EndTransactionMessage(String transactionName, boolean[] activity, boolean isError) {
			this.transactionName = transactionName;
			this.activity = activity;
			this.isError = isError;
		}

		protected EndTransactionMessage () {
			this(null, null, false);
		}
	}

	public static class AddNodeMessage implements Message, Serializable {

		private static final long serialVersionUID = 3116251573538148450L;
		public final int id;
		public final String name;
		public Type type;
		public final int parentId;
		public final int line;

		public AddNodeMessage(int id, String name, Type type, int parentId, int line) {
			this.id = id;
			this.name = name;
			this.type = type;
			this.parentId = parentId;
			this.line = line;
		}

		protected AddNodeMessage() {
			this(-1,null,null,-1,-1);
		}
	}

	public static class AddProbeMessage implements Message, Serializable {

		private static final long serialVersionUID = 5328295583566424138L;
		public final int id;
		public final int nodeId;

		public AddProbeMessage(int id, int nodeId) {
			this.id = id;
			this.nodeId = nodeId;
		}

		protected AddProbeMessage() {
			this(-1,-1);
		}
	}
}
