package org.grails.forge.feature.assetPipeline

import org.grails.forge.ApplicationContextSpec
import org.grails.forge.BuildBuilder
import org.grails.forge.application.ApplicationType
import org.grails.forge.feature.Features
import org.grails.forge.fixture.CommandOutputFixture
import org.grails.forge.options.BuildTool
import org.grails.forge.options.JdkVersion
import org.grails.forge.options.Language
import org.grails.forge.options.Options
import org.grails.forge.options.TestFramework

class AssetPipelineSpec extends ApplicationContextSpec implements CommandOutputFixture {

    void "test asset-pipeline-grails feature"() {
        when:
        final Features features = getFeatures(["asset-pipeline-grails"])

        then:
        features.contains("asset-pipeline-grails")
    }

    void "test dependencies are present for gradle"() {
        when:
        final String template = new BuildBuilder(beanContext)
                .features(["asset-pipeline-grails"])
                .render()

        then:
        template.contains("id \"com.bertramlabs.asset-pipeline\" version \"3.3.4\"")
        template.contains("runtimeOnly(\"com.bertramlabs.plugins:asset-pipeline-grails:3.3.4\")")
        template.contains('''
assets {
    minifyJs = true
    minifyCss = true
}''')
    }

    void "test assets files are present"() {
        given:
        final Map<String, String> output = generate(ApplicationType.DEFAULT, new Options(Language.GROOVY, TestFramework.SPOCK, BuildTool.GRADLE, JdkVersion.JDK_11))

        expect:
        output.containsKey("grails-app/assets/images/advancedgrails.svg")
        output.containsKey("grails-app/assets/images/apple-touch-icon.png")
        output.containsKey("grails-app/assets/images/apple-touch-icon-retina.png")
        output.containsKey("grails-app/assets/images/documentation.svg")
        output.containsKey("grails-app/assets/images/favicon.ico")
        output.containsKey("grails-app/assets/images/grails.svg")
        output.containsKey("grails-app/assets/images/grails-cupsonly-logo-white.svg")
        output.containsKey("grails-app/assets/images/slack.svg")
        output.containsKey("grails-app/assets/images/spinner.gif")
        output.containsKey("grails-app/assets/images/skin/database_add.png")
        output.containsKey("grails-app/assets/images/skin/database_delete.png")
        output.containsKey("grails-app/assets/images/skin/database_edit.png")
        output.containsKey("grails-app/assets/images/skin/database_save.png")
        output.containsKey("grails-app/assets/images/skin/database_table.png")
        output.containsKey("grails-app/assets/images/skin/exclamation.png")
        output.containsKey("grails-app/assets/images/skin/house.png")
        output.containsKey("grails-app/assets/images/skin/information.png")
        output.containsKey("grails-app/assets/images/skin/sorted_asc.gif")
        output.containsKey("grails-app/assets/images/skin/sorted_desc.gif")
        output.containsKey("grails-app/assets/javascripts/application.js")
        output.containsKey("grails-app/assets/javascripts/bootstrap.bundle.js")
        output.containsKey("grails-app/assets/javascripts/bootstrap.js")
        output.containsKey("grails-app/assets/javascripts/jquery-3.5.1.js")
        output.containsKey("grails-app/assets/javascripts/popper.js")
        output.containsKey("grails-app/assets/stylesheets/application.css")
        output.containsKey("grails-app/assets/stylesheets/bootstrap.css")
        output.containsKey("grails-app/assets/stylesheets/bootstrap-grid.css")
        output.containsKey("grails-app/assets/stylesheets/bootstrap-reboot.css")
        output.containsKey("grails-app/assets/stylesheets/errors.css")
        output.containsKey("grails-app/assets/stylesheets/grails.css")
        output.containsKey("grails-app/assets/stylesheets/main.css")
        output.containsKey("grails-app/assets/stylesheets/mobile.css")
    }
}