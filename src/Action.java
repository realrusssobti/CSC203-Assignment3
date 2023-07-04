public abstract class Action{
        protected Executable entity;
        protected int repeatCount;

        public Action(Executable entity, int repeatCount) {
            this.entity = entity;
            this.repeatCount = repeatCount;
        }

        public abstract void executeAction(EventScheduler scheduler);


}
