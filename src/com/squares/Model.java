package com.squares;

public abstract class Model extends CollidableSceneNode {
        public Model() {
                this.initializeTransform();
        }

        public void update(long ticks) {
                for(RenderableSceneNode sceneNode : getRenderableChildren()) {
                        sceneNode.update(ticks);
                }
        }

        public abstract void render();
}
