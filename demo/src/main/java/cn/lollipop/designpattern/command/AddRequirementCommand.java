package cn.lollipop.designpattern.command;

public class AddRequirementCommand extends Command {
    private RequirementGroup rg = new RequirementGroup();

    @Override
    public void execute() {
        rg.find();
        rg.add();
        rg.plan();
    }
}