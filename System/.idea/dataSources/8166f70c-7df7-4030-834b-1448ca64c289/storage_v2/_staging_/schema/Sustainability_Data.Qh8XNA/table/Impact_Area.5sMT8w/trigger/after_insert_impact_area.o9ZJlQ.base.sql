create definer = root@localhost trigger after_insert_impact_area
    after insert
    on Impact_Area
    for each row
begin
    DECLARE id INT;
	SET id = NEW.id;

-- Insert default Credits for Curriculum
IF NEW.name = 'Curriculum' THEN
        INSERT INTO Credit (impact_area_id, name, indicator) VALUES
                                                                 (id, 'Sustainability Course Offerings', 'Percentage of academic departments with sustainability course offerings. Support for academic employees to integrate sustainability into the curriculum. Published sustainability course listings.'),
                                                                 (id, 'Undergraduate Programs', 'Percentage of undergraduate qualifications awarded that have sustainability-focused learning requirements. Institutional sustainability learning outcomes for undergraduate students. Undergraduate programs with sustainability-focused learning requirements.'),
                                                                 (id, 'Graduate Programs', 'Percentage of graduate qualifications awarded that have sustainability-focused learning requirements. Graduate programs with sustainability-focused learning requirements.'),
                                                                 (id, 'Applied Learning', 'Applied learning for sustainability program.'),
                                                                 (id, 'Sustainability Literacy Assessment', 'Sustainability literacy assessment design and administration. Percentage of students assessed for sustainability literacy.');
    ELSEIF NEW.name = 'Research' THEN
        INSERT INTO Credit (impact_area_id, name, indicator) VALUES
                                                                 (id, 'Sustainability Research', 'Percentage of academic departments engaged in sustainability research. Incentives for sustainability research.'),
                                                                 (id, 'Responsible Research and Innovation', 'Recognition of integrated, community-based, and extra-academic research. Support for open access publishing. Published ethical code of conduct for research. Inter-campus collaboration for responsible research and innovation.'),
                                                                 (id, 'Sustainability Research Center', 'Organized sustainability research center, institute, or unit.');
    ELSEIF NEW.name = 'Campus Engagement' THEN
        INSERT INTO Credit (impact_area_id, name, indicator) VALUES
                                                                 (id, 'Co-Curricular Activities', 'Percentage of students that participate in sustainability activities. Sustainability-focused co-curricular activities. Student sustainability organization.'),
                                                                 (id, 'Staff Engagement and Training', 'Percentage of employees that participate in sustainability activities. Sustainability-focused training for employees. Staff sustainability network or engagement program.'),
                                                                 (id, 'Outreach and Communications', 'Sustainability outreach and communications. Percentage of campus stakeholders reached through sustainability outreach and communications.'),
                                                                 (id, 'Sustainability Culture Assessment', 'Sustainability culture assessment design and administration. Percentage of students assessed for sustainability culture. Percentage of employees assessed for sustainability culture.');
    ELSEIF NEW.name = 'Public Engagement' THEN
        INSERT INTO Credit (impact_area_id, name, indicator) VALUES
                                                                 (id, 'Community Partnerships', 'Partnerships to support underrepresented groups and vulnerable populations. Sustainability-focused community partnerships. Community partnership assessment.'),
                                                                 (id, 'Civic Engagement', 'Percentage of students that participate in civic engagement programs. Support for public policies to advance sustainability. Employee community service program.'),
                                                                 (id, 'Continuing Education', 'Continuing education offerings in sustainability.'),
                                                                 (id, 'Inter-Campus Collaboration', 'Inter-campus collaboration for sustainability.'),
                                                                 (id, 'Shared Facilities', 'Publicly shared facilities.');
    ELSEIF NEW.name = 'Buildings & Grounds' THEN
        INSERT INTO Credit (impact_area_id, name, indicator) VALUES
                                                                 (id, 'Water Use', ''),
                                                                 (id, 'Ecologically Managed Grounds', ''),
                                                                 (id, 'Building Operations and Maintenance', ''),
                                                                 (id, 'Building Design and Construction', '');
    ELSEIF NEW.name = 'Energy & Climate' THEN
        INSERT INTO Credit (impact_area_id, name, indicator) VALUES
                                                                 (id, 'Greenhouse Gas Emissions', ''),
                                                                 (id, 'Energy Use', '');
    ELSEIF NEW.name = 'Food & Dining' THEN
        INSERT INTO Credit (impact_area_id, name, indicator) VALUES
                                                                 (id, 'Dining Service Procurement', ''),
                                                                 (id, 'Food Recovery', '');
    ELSEIF NEW.name = 'Procurement & Waste' THEN
        INSERT INTO Credit (impact_area_id, name, indicator) VALUES
                                                                 (id, 'Sustainable Procurement System', ''),
                                                                 (id, 'Materials Management', ''),
                                                                 (id, 'Waste Generation and Recovery', ''),
                                                                 (id, 'Purchased Goods', '');
    ELSEIF NEW.name = 'Transportation' THEN
        INSERT INTO Credit (impact_area_id, name, indicator) VALUES
                                                                 (id, 'Commute Modal Split', ''),
                                                                 (id, 'Air Travel', ''),
                                                                 (id, 'Campus Fleet', '');
    ELSEIF NEW.name = 'Coordination & Planning' THEN
        INSERT INTO Credit (impact_area_id, name, indicator) VALUES
                                                                 (id, 'Commitments and Planning', ''),
                                                                 (id, 'Institutional Governance', ''),
                                                                 (id, 'Sustainability Coordination', '');
    ELSEIF NEW.name = 'Investment' THEN
        INSERT INTO Credit (impact_area_id, name, indicator) VALUES
                                                                 (id, 'Investment Holdings', ''),
                                                                 (id, 'Sustainable Investment Program', '');
    ELSEIF NEW.name = 'Social Equity' THEN
        INSERT INTO Credit (impact_area_id, name, indicator) VALUES
                                                                 (id, 'Racial and Ethnic Diversity', ''),
                                                                 (id, 'Student Success', ''),
                                                                 (id, 'Affordability and Access', ''),
                                                                 (id, 'Institutional Climate', ''),
                                                                 (id, 'Gender Parity', '');
    ELSEIF NEW.name = 'Wellbeing & Work' THEN
        INSERT INTO Credit (impact_area_id, name, indicator) VALUES
                                                                 (id, 'Pay Equity and Living Wage', ''),
                                                                 (id, 'Employee Rights', ''),
                                                                 (id, 'Health, Safety and Wellbeing', '');
    ELSEIF NEW.name = 'Innovation & Leadership' THEN
        INSERT INTO Credit (impact_area_id, name, indicator) VALUES
            (id, 'Innovation & Leadership', '');
    END IF;
end;

