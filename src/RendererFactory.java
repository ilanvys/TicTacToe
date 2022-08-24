public class RendererFactory {
    RendererFactory() {}

    /**
     * a Player factory that returns instance of the renderer requested.
     * @param rendererType name of the renderer.
     */
    public Renderer buildRenderer(String rendererType) {
        switch (rendererType) {
            case "console":
                return new ConsoleRenderer();
            case "none":
                return new VoidRenderer();
            default:
                return null;
        }
    }

}
