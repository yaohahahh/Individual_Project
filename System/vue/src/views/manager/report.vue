<template>
  <div>
    <h1>{{ data.name }}</h1>
    <h2>{ </h2>

    <div class="card" style="margin-bottom: 10px">
      <el-descriptions direction="vertical" :column="4" :size="size" border>
        <el-descriptions-item label="Rating">
          <el-tag type="info">Gold</el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="Score">Score</el-descriptions-item>
        <el-descriptions-item label="Submitted Date">
        </el-descriptions-item>

      </el-descriptions>
    </div>
    <div class="card" style="margin-bottom: 10px">

      <div class="card" style="margin-bottom: 10px">
        <h3>Society</h3>
        <div class="demo-collapse">

          <el-collapse v-model="activeNames" @change="handleChange">
            <el-collapse-item title="Academics" name="1">

              <el-collapse-item title="Curriculum point/40.0" name="1-1">
                <el-table :data="CurriculumList" stripe style="width: 100%" >
                  <el-table-column prop="credit" label="Credit" width="160" />
                  <el-table-column prop="indicator" label="Indicator"  />
                  <el-table-column prop="point" label="Point" width="60"/>
                </el-table>
              </el-collapse-item>

              <el-collapse-item title="Research point/18.0" name="1-2">
                <el-table :data="ResearchList" stripe style="width: 100%" >
                  <el-table-column prop="credit" label="Credit" width="160" />
                  <el-table-column prop="indicator" label="Indicator"  />
                  <el-table-column prop="point" label="Point" width="60"/>
                </el-table>
              </el-collapse-item>
            </el-collapse-item>
          </el-collapse>

          <el-collapse v-model="activeNames" @change="handleChange">
            <el-collapse-item title="Engagement" name="2">
              <el-collapse-item title="Campus Engagement point/21.0" name="2-1">
                <el-table :data="CampusList" stripe style="width: 100%" >
                  <el-table-column prop="credit" label="Credit" width="160" />
                  <el-table-column prop="indicator" label="Indicator"  />
                  <el-table-column prop="point" label="Point" width="60"/>
                </el-table>
              </el-collapse-item>

              <el-collapse-item title="Public Engagement point/20.0" name="2-2">
                <el-table :data="PublicList" stripe style="width: 100%" >
                  <el-table-column prop="credit" label="Credit" width="160" />
                  <el-table-column prop="indicator" label="Indicator"  />
                  <el-table-column prop="point" label="Point" width="60"/>
                </el-table>
              </el-collapse-item>
            </el-collapse-item>

          </el-collapse>

        </div>
      </div>

      <div class="card" style="margin-bottom: 10px">
        <h3>Economy</h3>
        <el-collapse v-model="activeNames" @change="handleChange">
          <el-collapse-item title="Planning & Administration" name="3">

            <el-collapse-item title="Coordination & Planning point/40.0" name="3-1">
              <el-table :data="CPList" stripe style="width: 100%" >
                <el-table-column prop="credit" label="Credit" width="160" />
                <el-table-column prop="indicator" label="Indicator"  />
                <el-table-column prop="point" label="Point" width="60"/>
              </el-table>
            </el-collapse-item>

            <el-collapse-item title="Investment point/18.0" name="3-2">
              <el-table :data="InvestmentList" stripe style="width: 100%" >
                <el-table-column prop="credit" label="Credit" width="160" />
                <el-table-column prop="indicator" label="Indicator"  />
                <el-table-column prop="point" label="Point" width="60"/>
              </el-table>
            </el-collapse-item>

            <el-collapse-item title="Social Equity point/40.0" name="3-3">
              <el-table :data="SEList" stripe style="width: 100%" >
                <el-table-column prop="credit" label="Credit" width="160" />
                <el-table-column prop="indicator" label="Indicator"  />
                <el-table-column prop="point" label="Point" width="60"/>
              </el-table>
            </el-collapse-item>

            <el-collapse-item title="Wellbeing & Work point/18.0" name="3-4">
              <el-table :data="WWList" stripe style="width: 100%" >
                <el-table-column prop="credit" label="Credit" width="160" />
                <el-table-column prop="indicator" label="Indicator"  />
                <el-table-column prop="point" label="Point" width="60"/>
              </el-table>
            </el-collapse-item>
          </el-collapse-item>
        </el-collapse>

        <el-collapse v-model="activeNames" @change="handleChange">
          <el-collapse-item title="Innovation & Leadership" name="4">
            <el-collapse-item title="Innovation & Leadership point/21.0" name="4-1">
              <el-table :data="ILList" stripe style="width: 100%" >
                <el-table-column prop="credit" label="Credit" width="160" />
                <el-table-column prop="indicator" label="Indicator"  />
                <el-table-column prop="point" label="Point" width="60"/>
              </el-table>
            </el-collapse-item>

          </el-collapse-item>

        </el-collapse>

      </div>

      <div class="card" style="margin-bottom: 10px">
        <h3>Environment</h3>
        <el-collapse v-model="activeNames" @change="handleChange">
          <el-collapse-item title="Operations" name="5">
            <el-collapse-item title="Buildings & Grounds point/21.0" name="5-1">
              <el-table :data="BGList" stripe style="width: 100%" >
                <el-table-column prop="credit" label="Credit" width="160" />
                <el-table-column prop="indicator" label="Indicator"  />
                <el-table-column prop="point" label="Point" width="60"/>
              </el-table>
            </el-collapse-item>
            <el-collapse-item title="Energy & Climate point/40.0" name="5-2">
              <el-table :data="ECList" stripe style="width: 100%" >
                <el-table-column prop="credit" label="Credit" width="160" />
                <el-table-column prop="indicator" label="Indicator"  />
                <el-table-column prop="point" label="Point" width="60"/>
              </el-table>
            </el-collapse-item>

            <el-collapse-item title="Food & Dining point/18.0" name="5-3">
              <el-table :data="FDList" stripe style="width: 100%" >
                <el-table-column prop="credit" label="Credit" width="160" />
                <el-table-column prop="indicator" label="Indicator"  />
                <el-table-column prop="point" label="Point" width="60"/>
              </el-table>
            </el-collapse-item>
            <el-collapse-item title="Procurement & Waste point/40.0" name="5-4">
              <el-table :data="PWList" stripe style="width: 100%" >
                <el-table-column prop="credit" label="Credit" width="160" />
                <el-table-column prop="indicator" label="Indicator"  />
                <el-table-column prop="point" label="Point" width="60"/>
              </el-table>
            </el-collapse-item>

            <el-collapse-item title="Transportation point/18.0" name="5-5">
              <el-table :data="TransportationList" stripe style="width: 100%" >
                <el-table-column prop="credit" label="Credit" width="160" />
                <el-table-column prop="indicator" label="Indicator"  />
                <el-table-column prop="point" label="Point" width="60"/>
              </el-table>
            </el-collapse-item>

          </el-collapse-item>

        </el-collapse>


      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import request from "../../utils/request";
import { reactive, onMounted, ref, computed, watch } from 'vue';
import { useRoute } from 'vue-router';


const route = useRoute();
const id = route.params.id;

const data = reactive({
  name: '',
  full_name: '',
  tableData: [],
  form: {}
});


// Load data function
const load = () => {
  request.get(`/list/institution/${id}`, {
    /* params: {
      name: data.name,
    },*/
  }).then((res) => {
        console.log(res);
        data.name = res.data.data.name;
        data.full_name = res.data.data.full_name;
        data.tableData = res.data?.list || [];
      })
      .catch((error) => {
        console.error('Error loading data:', error);
      });
};

onMounted(load);

// Collapse panel
const activeNames = ref(['1']);
const handleChange = (val: string | string[]) => {
  console.log(val);
};

// List
const size = ref('default');


interface Credit {
  credit: string;
  indicator: string;
  point: number;
}

const CurriculumList: Credit[] = [
  {
    credit: 'Academic Courses',
    indicator: 'Institution offers sustainability course content as measured by the percentage of academic courses\n' +
        'offered that are sustainability-focused or sustainability-inclusive.',
    point: 14,
  },
  {
    credit: 'Learning Outcomes',
    indicator: 'This credit recognizes institutions that have adopted sustainability learning outcomes. Learning outcomes\n' +
        'help students develop specific sustainability knowledge and skills and provide institutions and accrediting\n' +
        'bodies with standards against which to assess student learning.',
    point: 8,
  },
  {
    credit: 'Undergraduate Program',
    indicator: 'This credit recognizes institutions that have formal, undergraduate-level degree programs focused on\n' +
        'sustainability. Developing such programs signals an institution’s commitment to sustainability.',
    point: 3,
  },
  {
    credit: 'Graduate Program',
    indicator: 'This credit recognizes institutions that have formal, graduate academic degree programs focused on\n' +
        'sustainability. Developing such programs signals an institution’s commitment to sustainability.',
    point: 3,
  },
  {
    credit: 'Immersive Experience',
    indicator: 'This credit recognizes institutions that offer sustainability-focused immersive experience programs.\n' +
        'Sustained immersive experiences such as community-based internships and “study abroad” programs\n' +
        'give students the opportunity to witness and learn in-depth about sustainability challenges and solutions.',
    point: 2,
  },
  {
    credit: 'Sustainability Literacy Assessment',
    indicator: 'This credit recognizes institutions that are assessing the sustainability literacy of their students. Such an\n' +
        'assessment helps institutions evaluate the success of their sustainability education initiatives and develop\n' +
        'insight into how these initiatives could be improved.',
    point: 4,
  },
  {
    credit: 'Incentives for Developing Courses',
    indicator: 'Institution offers sustainability course content as measured by the percentage of academic courses\n' +
        'offered that are sustainability-focused or sustainability-inclusive.',
    point: 2,
  },
  {
    credit: 'Campus as a Living Laboratory',
    indicator: 'This credit recognizes institutions that utilize their infrastructure and operations as living environments for\n' +
        'multidisciplinary learning and applied research that advances sustainability on campus. Students that\n' +
        'actively participate in making their campuses more sustainable are well prepared to continue that work in\n' +
        'their careers and communities after graduation.',
    point: 4,
  },
];

const ResearchList: Credit[] = [
  {
    credit: '',
    indicator: '',
    point: 1,
  },
];

const CampusList: Credit[] = [
  {
    credit: '',
    indicator: '',
    point: 1,
  },
];

const PublicList: Credit[] = [
  {
    credit: '',
    indicator: '',
    point: 1,
  },
];

const CPList: Credit[] = [
  {
    credit: '',
    indicator: '',
    point: 1,
  },
];

const InvestmentList: Credit[] = [
  {
    credit: '',
    indicator: '',
    point: 1,
  },
];

const SEList: Credit[] = [
  {
    credit: '',
    indicator: '',
    point: 1,
  },
];

const WWList: Credit[] = [
  {
    credit: '',
    indicator: '',
    point: 1,
  },
];

const ILList: Credit[] = [
  {
    credit: '',
    indicator: '',
    point: 1,
  },
];

const BGList: Credit[] = [
  {
    credit: '',
    indicator: '',
    point: 1,
  },
];

const ECList: Credit[] = [
  {
    credit: '',
    indicator: '',
    point: 1,
  },
];

const FDList: Credit[] = [
  {
    credit: '',
    indicator: '',
    point: 1,
  },
];

const PWList: Credit[] = [
  {
    credit: '',
    indicator: '',
    point: 1,
  },
];

const TransportationList: Credit[] = [
  {
    credit: '',
    indicator: '',
    point: 1,
  },
];


</script>
