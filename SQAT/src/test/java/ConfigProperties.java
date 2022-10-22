import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "file:${user.dir}/src/main/resources/config.properties",

})
public interface ConfigProperties extends Config{

    String browser();

}
