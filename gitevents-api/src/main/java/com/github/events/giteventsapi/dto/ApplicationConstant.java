package com.github.events.giteventsapi.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApplicationConstant {

	public static final List<String> eventTypes = new ArrayList<>(Arrays.asList(

			"CheckRunEvent", "CheckSuiteEvent", "CommitCommentEvent", "ContentReferenceEvent", "CreateEvent", "DeleteEvent", "DeployKeyEvent",
			"DeploymentEvent", "DeploymentStatusEvent", "DownloadEvent", "FollowEvent", "ForkEvent", "ForkApplyEvent", "GitHubAppAuthorizationEvent",
			"GistEvent", "GollumEvent", "InstallationEvent", "InstallationRepositoriesEvent", "IssueCommentEvent", "IssuesEvent", "LabelEvent",
			"MarketplacePurchaseEvent", "MemberEvent", "MembershipEvent", "MetaEvent", "MilestoneEvent", "OrganizationEvent", "OrgBlockEvent", "PageBuildEvent",
			"ProjectCardEvent", "ProjectColumnEvent", "ProjectEvent", "PublicEvent", "PullRequestEvent", "PullRequestReviewEvent",
			"PullRequestReviewCommentEvent", "PushEvent", "ReleaseEvent", "RepositoryEvent", "RepositoryImportEvent", "RepositoryVulnerabilityAlertEvent",
			"SecurityAdvisoryEvent", "StarEvent", "StatusEvent", "TeamEvent", "TeamAddEvent", "WatchEvent"));

}
